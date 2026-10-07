package keml.diagram;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Set;

import org.eclipse.sirius.components.collaborative.diagrams.ILayoutStrategyDeserializer;
import org.eclipse.sirius.components.collaborative.diagrams.INodeStyleDeserializer;
import org.eclipse.sirius.components.diagrams.CollapsingState;
import org.eclipse.sirius.components.diagrams.FreeFormLayoutStrategy;
import org.eclipse.sirius.components.diagrams.ILayoutStrategy;
import org.eclipse.sirius.components.diagrams.INodeStyle;
import org.eclipse.sirius.components.diagrams.Node;
import org.eclipse.sirius.components.diagrams.ViewModifier;
import org.eclipse.sirius.components.diagrams.components.BorderNodePosition;
import org.eclipse.sirius.components.representations.VariableManager;
import org.eclipse.sirius.components.view.diagram.DiagramDescription;
import org.junit.jupiter.api.Test;

import tools.jackson.databind.MapperFeature;
import tools.jackson.databind.json.JsonMapper;
import tools.jackson.databind.module.SimpleModule;

import graphql.GraphQL;
import graphql.Scalars;
import graphql.schema.GraphQLObjectType;
import graphql.schema.GraphQLSchema;

class CylinderNodeStyleTests {
    @Test
    void contributesOnlyTheCylinderAndRoundTripsItsPersistedNode() {
        var provider = new CylinderNodeStyleProvider();
        var view = (DiagramDescription) new KemlViews().create().getDescriptions().getFirst();
        var description = view.getNodeDescriptions().stream().filter(node -> "PreKnowledgeNode".equals(node.getName())).findFirst().orElseThrow();
        var style = provider.createNodeStyle(description.getStyle(), new FreeFormLayoutStrategy(), null, new VariableManager()).orElseThrow();
        assertThat(provider.getNodeType(description.getStyle())).contains(CylinderNodeStyleProvider.NODE_TYPE);
        assertThat(provider.getNodeType(view.getNodeDescriptions().getFirst().getStyle())).isEmpty();
        assertThat(provider.canHandle("image")).isFalse();
        var node = Node.newNode("cylinder").type(CylinderNodeStyleProvider.NODE_TYPE).style(style)
                .targetObjectId("knowledge").targetObjectKind("keml").targetObjectLabel("Knowledge").descriptionId("description")
                .initialBorderNodePosition(BorderNodePosition.NONE).modifiers(Set.of()).state(ViewModifier.Normal)
                .collapsingState(CollapsingState.EXPANDED).borderNodes(List.of()).childNodes(List.of())
                .customizedStyleProperties(Set.of()).decorators(List.of()).build();
        // Use the same polymorphic deserializers and property order as Sirius's application mapper.
        var module = new SimpleModule();
        module.addDeserializer(INodeStyle.class, new INodeStyleDeserializer(List.of(provider)));
        module.addDeserializer(ILayoutStrategy.class, new ILayoutStrategyDeserializer());
        var mapper = JsonMapper.builder().disable(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY).addModule(module).build();
        var restored = mapper.readValue(mapper.writeValueAsString(node), Node.class);
        assertThat(restored.getType()).isEqualTo(CylinderNodeStyleProvider.NODE_TYPE);
        assertThat(restored.getStyle()).isInstanceOf(CylinderNodeStyle.class);
        var restoredStyle = (CylinderNodeStyle) restored.getStyle();
        assertThat(restoredStyle.background()).isEqualTo("#ffffa0");
        assertThat(restoredStyle.borderSize()).isEqualTo(1);
        assertThat(restoredStyle.getChildrenLayoutStrategy()).isInstanceOf(FreeFormLayoutStrategy.class);
        // GraphQL's default property fetcher must also expose record components (no custom fetchers).
        var cylinderType = GraphQLObjectType.newObject().name("CylinderNodeStyle")
                .field(field -> field.name("background").type(Scalars.GraphQLString))
                .field(field -> field.name("borderSize").type(Scalars.GraphQLInt)).build();
        var query = GraphQLObjectType.newObject().name("Query")
                .field(field -> field.name("cylinder").type(cylinderType).dataFetcher(environment -> restoredStyle)).build();
        var result = GraphQL.newGraphQL(GraphQLSchema.newSchema().query(query).build()).build()
                .execute("{ cylinder { background borderSize } }");
        assertThat(result.getErrors()).isEmpty();
        assertThat(result.<Object>getData()).isEqualTo(java.util.Map.of("cylinder", java.util.Map.of("background", "#ffffa0", "borderSize", 1)));
    }
}
