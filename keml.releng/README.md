# KEML Sirius Web sample

This sample runs the KEML conversation and knowledge editor as its own Sirius Web
application. It uses Java 21, Spring Boot and PostgreSQL, following Guesstimate's
application setup. The custom frontend extends Sirius Web's published UI packages with conversation analysis.

## Projects and responsibilities

| Project | Purpose |
| --- | --- |
| `keml.releng` | Parent POM, complete reactor, shared versions and Docker setup. |
| `keml` | Ecore metamodel and generated EMF model classes. |
| `keml.edit` | Generated/customized EMF item providers for labels, icons and properties. |
| `keml.io` | Existing KEML/JSON file loading used by analysis and the upstream command-line tools. |
| `keml.analysis` | Conversation/argument statistics and trust-scenario workbooks, reusable as a library. |
| `keml.diagram` | Reusable Sirius diagram description, tools, layout and example project template. |
| `keml.frontend` | Sirius Web workbench with the KEML analysis dialog, built with React/Vite. |
| `keml.app` | Spring Boot launcher, EMF registration, analysis endpoints and runtime configuration. |

The diagram module remains reusable in Guesstimate. The standalone application has
no dependency on Guesstimate's application or configuration projects.
The app disables Sirius Studio's authoring templates with `sirius.web.disabled=portal,studio`:
users create KEML projects, while developers define the model and views in Java/EMF.

## Source repositories and baseline commits

The pre-existing repositories were cloned from the sources below. These exact
starting revisions were verified against each local repository's original clone
reflog before consolidating the code into this single repository. The individual
`.git` directories have been removed; this table preserves their provenance.

| Directory | Upstream repository | Baseline commit |
| --- | --- | --- |
| `keml` | [keml-group/keml](https://github.com/keml-group/keml) | [ef1be4b3917a54328d8dd736aaab5db201c53618](https://github.com/keml-group/keml/commit/ef1be4b3917a54328d8dd736aaab5db201c53618) |
| `keml.edit` | [keml-group/keml.edit](https://github.com/keml-group/keml.edit) | [485ce97fd98971b90f787a35a377eff90b0785ec](https://github.com/keml-group/keml.edit/commit/485ce97fd98971b90f787a35a377eff90b0785ec) |
| `keml.io` | [keml-group/keml.io](https://github.com/keml-group/keml.io) | [3b9f470c88d97374612b1c025286fbee41d1f018](https://github.com/keml-group/keml.io/commit/3b9f470c88d97374612b1c025286fbee41d1f018) |
| `keml.analysis` | [keml-group/keml.analysis](https://github.com/keml-group/keml.analysis) | [496b9566ed3b8a8a58c310ebfa082b8346850a75](https://github.com/keml-group/keml.analysis/commit/496b9566ed3b8a8a58c310ebfa082b8346850a75) |
| `web-editor` | [keml-group/web-editor](https://github.com/keml-group/web-editor) | [ff55e927b7f91dbbee1d209cd4006703fbcc7741](https://github.com/keml-group/web-editor/commit/ff55e927b7f91dbbee1d209cd4006703fbcc7741) |

The Maven/Eclipse setup, item-provider and icon updates, and analysis integration
are local adaptations on top of those commits. `keml.diagram`, `keml.frontend`,
`keml.app` and `keml.releng` were created in this workspace for the Sirius Web
sample. `web-editor` was consulted as a reference checkout and is not part of the
Maven reactor.

## Import into Eclipse

Use Eclipse with Java 21, m2e and EMF development tools. Choose **File → Import →
Maven → Existing Maven Projects**, select the directory containing these eight
projects, and import all eight. Each project has a checked-in `.project`; m2e
derives Java source folders, dependencies and compiler settings from its POM.

The generated model classes under `keml/src-gen` are included in this repository,
so a fresh checkout builds without a separate EMF generation step. When changing
the metamodel, open `keml/model/keml.genmodel`, select the root and choose
**Generate Model Code**, then include the updated generated sources. Preserve the
customized item providers in `keml.edit`; generating Edit Code is unnecessary.
Select the projects and run **Maven → Update Project**.

`keml.app/KEML Web Application.launch` is a shared standard Java launch using the
`dev` profile. It works without Spring Tools. Start the database as described below
before running or debugging it.

## Build the whole reactor

Run these commands from the directory containing the projects:

```sh
mvn -f keml.releng/pom.xml clean verify
```

This builds and tests the model, edit support, I/O, analysis and diagram, then produces the
executable **`keml.app/target/keml.jar`**. Maven also installs a local Node version,
runs `npm ci`, type-checks and bundles `keml.frontend`, and packages its assets in
the executable JAR. Eclipse imports its Maven/Java metadata; frontend build
executions are ignored during automatic m2e builds. Maven needs access to the Sirius Web and
EMF JSON GitHub Packages repositories. Use your existing Maven credentials with
the server IDs `github-sirius-web` and `github-sirius-emfjson`; keep credentials in
your Maven settings, outside the projects. The frontend needs GitHub Packages npm access as well:
configure authentication for `//npm.pkg.github.com/` in your user `~/.npmrc`.
The project `.npmrc` contains registry names only, never credentials.

## Run with Java or Eclipse

```sh
docker compose -f keml.releng/docker-compose.yml up -d database
java -jar keml.app/target/keml.jar --spring.profiles.active=dev
```

Open <http://localhost:8080>. PostgreSQL listens on port 5434, with database
`keml-db` and local sample username/password `keml`/`keml`. This database is separate
from Guesstimate's. To use another database without the dev profile, set
`SPRING_DATASOURCE_URL`, `SPRING_DATASOURCE_USERNAME` and `SPRING_DATASOURCE_PASSWORD`.
Liquibase initializes and upgrades Sirius Web's schema on startup.
The sample does not require Elasticsearch: search uses Sirius Web's model-based
fallback and the optional Elasticsearch health indicator is disabled.

Create a project using **KEML Conversation**. Expand its model in the explorer,
select **Log4j repository search**, and create **KemlConversationDiagram**.
The bold Author label and thicker black lifeline distinguish the central participant.
Partner palettes offer **Send to Author** and **Receive from Author**. Tools on
the Author or diagram background ask you to choose the partner explicitly.
**Reply** reverses a message's direction and keeps the same partner; received
messages create knowledge or instructions. Knowledge-card palettes create argument
links and references to messages; **Use in sent message** lists only messages sent
by the Author. Pre-knowledge boxes start at 390 pixels wide with 50% more height
than ordinary knowledge cards. You can resize them manually. The diagram toolbar
provides the standard navigation controls.
Double-click labels to edit their underlying text; the
Details view exposes the other model properties.

## Run everything in Docker

Build the Maven reactor first, then:

```sh
docker compose -f keml.releng/docker-compose.yml up --build -d
docker compose -f keml.releng/docker-compose.yml logs -f app
```

The runtime image contains Java 21 and the executable JAR and runs as a non-root
user. Compose waits for PostgreSQL's health check before starting the app. The
database uses a named volume, so stopping the services preserves your projects:

```sh
docker compose -f keml.releng/docker-compose.yml down
```

`KEML_HTTP_PORT`, `KEML_DATABASE_PORT` and `KEML_DATABASE_PASSWORD` override Compose's
local defaults. For example, `KEML_HTTP_PORT=8081` allows running beside Guesstimate.
When launching Java with a different database port/password, also override the
corresponding `SPRING_DATASOURCE_*` settings. Database initialization credentials
apply when PostgreSQL creates a new volume.

## Use this sample for another Sirius Web app

Start with `KemlApplication`: `@SpringBootApplication` enables the starter's
auto-configuration and scans `keml.app`. Its explicit `@Import` loads the diagram
configuration in another package. Keep these contributions explicit instead of
scanning all generated model classes.

`KemlEMFConfiguration` supplies two integration points: an `EPackage` bean lets
Sirius load and query the domain, while an adapter-factory descriptor supplies
EMF Edit labels, icons and property descriptions. Replace those beans with your
own generated package and item-provider factory. Custom generated validators can
also be registered with Sirius's `EValidator.Registry` when your domain needs them.

In `keml.diagram`, `KemlViews` builds the declarative view with Sirius's Java
builders. `KemlJavaServiceProvider` exposes `KemlServices` to its AQL expressions,
and `KemlViewEditingContextInitializer` installs the view in each workbench.
SVG shapes live under `src/main/resources/images/keml`. The view uses
`/images/keml/...` paths and `KemlDiagramConfiguration` contributes an
`IImagePathService` bean allowing that folder. Sirius serves these classpath
resources through its `/api/images` endpoint.
Model and creation-tool icons are native SVGs in `keml.edit/icons/full/obj16`,
mirrored under `keml.edit/src-gen/icons/full/obj16` for the Eclipse source folder.
Keep both copies identical when editing an icon. The item providers use explicit
`.svg` keys, and `KemlEditPlugin` handles SVG lookup in both standalone Java and
Eclipse. Customized generated methods carry `@generated NOT` so EMF preserves them.
`KemlViews` assigns those same `/icons/full/obj16/...svg` paths with
`iconURLsExpression` on each creation tool. Support and attack icons use the
diagram's green/red colours, with heavier arrows for strong links; edit and delete
retain Sirius's standard pencil and trash icons.
The project-template illustration is packaged under `src/main/resources/project-templates`,
Sirius's standard folder for project creation thumbnails.
`KemlDiagramPostProcessor` arranges the conversation chronology using native
diagram layout data. The project-template provider and initializer create the
editable example model. Reuse the standard Sirius tools, persistence and frontend
before introducing custom endpoints or frontend components.

## Analyse and download a conversation

In the Explorer, expand the document, select the **Conversation** (for example,
**Log4j repository search**), open its **⋮** palette and choose **Analyse conversation…**. The dialog analyses the entire
conversation and shows:

* **Overview**: messages sent/received from each participant's perspective,
  interrupted replies, facts, instructions, pre-knowledge and repetitions.
* **Argumentation**: the attacks/supports matrix. Rows are sources and columns
  are targets; F/I distinguish facts and instructions. Strong links count as one
  link, and supplements are excluded, matching the CSV report.
* **Trust**: weights 2–10 and the four existing initial-trust presets. The Author
  starts at 1.0; presets referring to the LLM use the participant named `LLM`.
  Computed scores and recorded ratings are separate columns; absent recorded
  ratings display as a dash.

Changing weight/preset uses the same snapshot. **Recalculate** captures the current
conversation again, including subsequent edits. **Download reports ZIP** exports
the snapshot displayed in the dialog: two CSV files and nine Excel workbooks,
each containing the four presets. The download always includes the full batch,
regardless of the preview controls. Selecting the Conversation also exposes an
**Analysis → Download analysis reports (ZIP)** link in the native Details view;
that shortcut exports a fresh snapshot each time.

Calculated trust never changes the edited model. Circular argumentation produces
a specific error without partial trust results. Partner names must be distinct,
non-blank and different from the reserved report column name `Author`; messages
and argument links must refer to participants/knowledge in their conversation.

## How analysis is integrated

`KemlAnalysisConfiguration` registers the library's `AnalysisProvider` bean.
`KemlAnalysisSnapshotHandler` handles a dedicated read-only Sirius input and
copies the conversation on its editing-context thread. This is the important
boundary for a sample: never read mutable live EMF objects on a controller thread.
The controller runs calculations and file generation after capturing the copy,
without blocking the editor for workbook generation.

`AnalysisProvider.analyse(conversation)` returns structured values for the dialog.
It reuses the same counts, argumentation matrix and trust evaluator as the report
writers, rather than parsing spreadsheets back into browser data.
`AnalysisProvider.writeReports(conversation, directory)` remains available to
other Java applications; both entry points work on copies.

`KemlAnalysisMenuProvider` contributes the action to the native Explorer palette
and legacy context menu for Conversations. `keml.frontend/src/main.tsx` merges
its React implementations into Sirius's palette and context-menu extensions,
preserving existing entries. `AnalysisDialog.tsx` uses the
standard UI components and server context. The Details contribution reuses the
standard EMF properties and adds a native link, so other properties remain editable.

The application endpoints are under
`/api/editingcontexts/{editingContextId}/keml/conversations/{conversationId}`:

* `POST /analysis` captures and analyses a snapshot, returning its ID, timestamp
  and browser results.
* `GET /analysis/{snapshotId}/reports` downloads the corresponding snapshot's ZIP.
* `GET /reports` captures and downloads a fresh conversation for the Details link.

Downloads belong to the specified editing context and conversation. Snapshots are
kept in memory for up to 30 minutes, with a maximum of 32; a server restart,
expiry or eviction requires **Recalculate** before downloading. This small sample
has no persistent job queue or analysis database. Generated temporary files are
removed on success and failure, and responses are marked `no-store`.

Build the whole Maven reactor before running the frontend separately. Then use
`cd keml.frontend && npm start` for Vite development; its `/api` proxy targets the
Java app on port 8080, including subscriptions. Use `npm run build` for standalone
frontend type-checking/bundling once the reactor has extracted the base styles.
The frontend build also runs the cylinder geometry checks using Node's built-in
test runner. All Sirius npm packages are pinned to the same version as the backend. When
upgrading Sirius, update those versions and the lockfile together with the parent
POM. The build reuses the matching published frontend's global CSS and fonts.

### Contributing the pre-knowledge cylinder

`KemlViews` keeps the existing `PreKnowledgeNode` description and palettes, but
uses `keml:cylinder` as an image-style marker. `CylinderNodeStyleProvider` handles
that marker before Sirius's ordinary image provider, returning a custom runtime
style and node type. The module's `schema/keml-cylinder.graphqls` extends the
GraphQL style union, and the same provider registers a deserializer so the style
survives saving and reopening diagrams. No extension of the Ecore or View
metamodel is needed. The old SVG asset remains available for previously saved
image-style nodes until they refresh.

`CylinderContribution.ts` extends Sirius's diagram subscription to request the
custom style and delegates ordinary node conversion to the native converter.
`CylinderNode.tsx` supplies the React renderer through
`DiagramRepresentationConfiguration` in `main.tsx`, retaining Sirius's label
editing, palette actions, resizing and connection controls. The shared geometry
in `CylinderGeometry.ts` draws the caps, bounds the label inside the body, and
projects edge endpoints onto the outline. Text wraps with a multiline ellipsis;
resizing reveals more text, hovering shows the complete label, and direct edit
always uses the original message. The post-processor only imposes anchors on
lifelines and messages; knowledge edges preserve their saved anchor positions.

The optional Python comparison/histogram step is not invoked by the embedded
provider and the sample Docker image does not need Python. The upstream Python
`main.py` currently calls `insert_rand_values`, which replaces felt-trust ratings
with random demonstration values. Before offering **Compare with my ratings**,
remove that substitution, handle missing ratings, and add the Python runtime and
its dependencies. Standard CSV/Excel exports already preserve recorded ratings.

For compatibility, the upstream standalone analysis server remains available:

```sh
mvn -f keml.releng/pom.xml -Panalysis-server clean verify
java -jar keml.analysis/target/kemlanalysisserver-server.jar JAR /tmp/keml-analysis
```

The profile attaches an executable `server` JAR while retaining the ordinary
library JAR. Its application properties have a dedicated `analysis-server`
profile so they cannot override KEML app configuration. Its REST controller is
not imported into the sample app. For Python-enabled standalone execution, use
the analysis module's Dockerfile after building with the same Maven profile.

The app POM shows the packaging boundary: `sirius-web-starter` supplies the server,
`keml.frontend` packages the extended UI, and the Spring Boot Maven plugin packages
both together with the domain contributions. Only this launcher module is
repackaged by default; the model, diagram and analysis stay ordinary JARs that other apps can import.
