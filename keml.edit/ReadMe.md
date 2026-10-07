# KEML Edit -- ARCHIVED

This project holds edit code for KEML files.
It can be generated in a basic flavor from [keml](https://github.com/keml-group/keml). However, this version is advanced in the sense that it has custom icons that are used conditionally.

Since the development of a sirius-based editor that would have been built on top of the edit project was dropped in favor of an own web-based version, the maintenance of an extra edit project is not feasible any more. Hence, the project is ARCHIVED. You may use it, but it will not reflect new changes. In order to apply them, you will need to run the edit code generation on [keml](https://github.com/keml-group/keml), just as described under usage.

## Usage
The project itself is packed with all source files. In order to add the new flavor to an existing keml.editor, run Generate -> Editor code or Generate -> All on the keml ecore file as described on the [keml model project](https://github.com/keml-group/keml).
You can then start an Eclipse application from the keml.editor to use the edit code.

## Adding more customization
To add further customization, just go into the generated code and remove the `@Generated`annotation of the method you would like to adapt. Further generations will not touch this method any more.
Don't forget to run Generate->Editor Code (or All!) afterwards to have your changes available on an Eclipse editor spawned from the keml.editor.

## Further Reading
More information about model code, edit code and generation can be found on the Eclipse tutorial on [Domain Models](https://wiki.eclipse.org/Sirius/Tutorials/DomainModelTutorial). Further reading about customization, even going beyond edit code customization into Sirius graphical editors is available on the [Eclipse Sirius Starter Tutorial](https://wiki.eclipse.org/Sirius/Tutorials/StarterTutorial).

## Credits
The customized images were generated from [yEd](https://www.yworks.com/yed) files, we created. We jused [GIMP](https://www.gimp.org/) for image conversions.

## License
The license of this project is that of the [group](https://github.com/keml-group/keml).


