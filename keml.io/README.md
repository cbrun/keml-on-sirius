# KEML I/O

This project offers basic functionality for KEML file I/O as well as some more advanced routines on those files and auxiliaries:

1) [Generate KEML files from graphML files](#generate-keml-files-from-graphml-files)
2) [Split a ChatGPT conversation file](#split-a-chatgpt-conversation-file) holding all conversations into one file per conversation
3) [Enhance an KEML file with original conversation data](#enhance-an-keml-file-with-original-conversation-data) from the ChatGPT conversation JSON

Currently, all of these processes are executed when running the project but we plan to split this to allow for different work flows.
The enhancement with ChatGPT original conversations is optional, the run suceeds even if no source for original conversations is given.

## Installation

Being an EMF project, this project is best viewed and adapted from Eclipse. If you load it there, make sure that you have the right project natures, that is **modeling** and **maven**.
If you freshly added maven to this project in Eclipse, it might be necessary to run Maven -> Update project on it before using maven to install the necessary libraries.

## Running

This project is a basic maven based java application you can run in all normal ways (command line, IDE...).
It has one optional input: the base folder. If none is given, it executes the routines named above on the complete example from keml.sample - assuming that project is located on the same level as keml.io.

## Generate KEML Files from graphML Files
Given a base folder, the project converts all graphML files in the subfolder **graphml** into KEML files and puts these into a subfolder **keml** it creates if it does not exist yet.
The parser for the graphML files runs several checks and reports severe parsing errors as exceptions, effectively aborting the current fils's conversation.
However, further files are still processed.

### Common Parsing Error Sources
 - The parser determines which messages belong to a certain life line by checking their vertical position. Please make sure to have no messages exceed the vertical borders given by their respective life line icons. 
- GraphML edges are directed. Although the edge might "look right" because you added the arrow to the source of the edge (because you drew it the wrong direction), the parser uses the edge direction and will throw an error. The direction even counts on edges on which you have no arrow tip.
- An information message box and its icon indicating fact vs instruction need to be aligned almost perfectly.

## Split a ChatGPT Conversation File
This method expects to find a file `conv.json` in the given base folder. This file must convey to the JSON schema OpenAI uses to export all conversations.
It is a JSON Array holding JSONs for each conversation.
Our tool splits the array into single JSONs, using each conversation's name also as file name (with .json ending).

## Enhance an KEML File with Original Conversation Data
Under the given base folder this method expects to find a folder keml holding all relevant KEML files and another folder conv holding all original conversation JSONs.
It then searches for a matching conv file for each keml file and attaches the messages from the original conversation to the keml model as attribute "originalContent".
Note that this method only considers the KEML messages between the author and a conversation partner called "LLM".
Problems like not matching counts of these messages and those in the original conversation or a different message direction are reported, but cause no abort.

## Planned Features

We plan to modify the project so that the user can choose to run just one of the possible processes. With this, one could for instance enhance KEML files that have been generated programatically.

## License
The license of this project is that of the [group](https://github.com/keml-group).
