# KEML Analysis

## Integration with the KEML Sirius Web sample

This checkout inherits `../keml.releng/pom.xml` and builds as a reusable Java 21
library alongside `keml.io`, the model and the application. Import it using
**Maven → Existing Maven Projects** in Eclipse; `.project` declares Java and m2e
natures. Build the complete reactor with `mvn -f ../keml.releng/pom.xml clean verify`.

The sample app registers an `AnalysisProvider` bean. Its
`writeReports(Conversation, Path)` method exports CSV and Excel reports from a
copy of a live model, without changing the conversation's trust attributes.
Its `analyse(Conversation)` method returns structured statistics, argumentation
and trust scenarios for the browser. In the sample, choose **Analyse conversation…**
from a Conversation's Explorer context menu, or download reports from its Details
view. See `../keml.releng/README.md` for the user flow and optional Python limitations. The upstream command-line tools below remain available.
For an executable standalone server, build the whole reactor with
`-Panalysis-server`; the result is `target/kemlanalysisserver-server.jar`.

This project analyses KEML files statistically. For each KEML file it produces:
1) [General Statistics](#general-statistics)
2) [Argumentation Statistics](#argumentation-statistics)
3) [Trust Scores](#trust-scores)

## Installation

Since this project uses EMF components, it is best viewed and adapted from Eclipse. If you load it there, make sure that you have the right project natures, that is **modeling** and **maven**.
If you freshly added maven to this project in Eclipse, it might be necessary to run Maven -> Update project on it before using maven to install the necessary libraries.

## Running

This project is a basic maven based java application you can run in all normal ways (command line, IDE...). There are two classes in the **analysis** package with main methods that can be run in these ways:
The Main class has two optional inputs: a) a boolean flag runFurtherAnalysis deciding whether [Further Analysis](#further-analysis) is run and b) the path to the base folder. If none is given, the boolean flag is set to false and it creates statistics on the introductory example from keml.sample - assuming that project is located on the same level as keml.sample. If only one is given the argument will be the boolean flag.
Alternatively you can run the AnalysisProvider class. It has an additional optional input: the name of a single file that should be analyzed. If none is given, it assumes that there is only one file in the base path folder.
All output files are stored in the folder **analysis**.

## Server running

Instead of running the analysis directly on your device the project also offers the possiblity to run as a server so that you can access it via REST-API. There are 3 possible ways of running the Spring Boot Application server so that you can access it via REST-API:

- **Spring Boot App:** With the Spring Tools plugin installed in your Eclipse IDE you can run the KemlAnalysisServerApplication class as a Spring Boot App directly
- **Running JAR directly:** Build the complete reactor with `mvn -f ../keml.releng/pom.xml -Panalysis-server clean verify`, then run `java -jar target/kemlanalysisserver-server.jar JAR /tmp/keml-analysis`.
- **Running JAR with Docker:** Instead of running the JAR yourself you can use the provided Dockerfile and build a Docker image to run the JAR inside of a Docker container with port 8080. This has the advantage that you do not need to install any additional software on your system for [Further Analysis](#further-analysis)

The request must be sent as a HTTP POST request to \<IP-address\>:8080/api/process-json?runFurtherAnalysis=\<bool\> with a JSON body consisting of the content of the JSON file that should be analyzed. The flag runFurtherAnalysis is a boolean value having the same effect as described before.
If successful, the request returns a ZIP file including all the files that resulted from the analysis of the sent JSON. The returned file has the name input_\<timestamp\>.zip.
The first runtime argument when starting the application is the execution mode (STANDARD, JAR, DOCKER_JAR). When no argument is given STANDARD is used meaning that the program assumes it has been started as a Spring Boot App in Eclipse. When running the app with Docker the Dockerfile already includes the correct execution mode. Using an argument that does not equal one of the three named before, results in the application immediately shutting down.
The second optional argument is the path of where the files are stored temporarily before being sent to the client. If no second argument is given the app again uses keml.sample/introductoryExamples as a base path assuming as before that the project is located on the same level as keml.sample.

## Output
In **analysis**, each filename starts with a prefix _pre_ that is equal to the KEML file name.

Currently, three types of statistics are generated:
1) [General Statistics](#general-statistics)
2) [Argumentation Statistics](#argumentation-statistics)
3) [Trust Scores](#trust-scores)

### General Statistics
General statistics are stored under $pre$-general.csv.

This CSV file holds a Message Part and a Knowledge Part where it gives statistics per Conversation Partner. 
The Message Part gives counts for sends and receives, as well as interruptions.
The Knowledge Part counts PreKnowledge and New information, split into Facts and Instructions. It also counts repetitions.

![Example General Statistics](doc/example-general-csv-2.png)


### Argumentation Statistics
Argumentation statistics are stored under _pre_-arguments.csv.

This CSV file consists of a table that counts attacks and supports between facts (F) and instructions (I) of all conversation partners (including the human author).

![Example Argumentation Statistics](doc/example-arguments-csv.png)

### Trust Scores

Trust Scores are given as Excel (xlsx) files _pre_-w _n_--arguments.csv where _n_ is the weight of the trust computation formula.
Each file depicts four scenarios (a-d) described under [Initial Trust](#initial-trust).
Each scenario consists of two columns, one (iT) that lists the initial trust score for each information and one (T) that lists the (final) trust score.
Additionally, there are columns to describe the information i precisely:
1) The **time stamp** (-1 for pre knowledge) with the background color stating whether i is fact (green) or instruction (orange)
2) The **message** column with the background color blue for LLM messages and yellow for all other messages
3) The **argument count \#Arg** counting how many other information influence i directly
4) The **repetition count \#Rep** counting the number of repetitions of i

![Example Trust Scores](doc/example-trust-xlsx.png)


#### Trust computation formula
**Trust T** into an **information i** is computed based on **initial trust $T_{init}$** by combining it with a **repetition score $T_{rep}$** and an **argumentative trust $T_{arg}$**:

$T(i)= restrict(T_{init}(i) + T_{rep}(i) + w*T_{arg}(i))$

Here, restrict limits the computed trust to a value in [-1.0,... 1.0].
The weight $w$ is a natural number that controls the emphasis of $T_{arg}$. The analysis currently runs for $w\in[2,... 10]$.

#### Repetition Score

The phenomenon that someone trusts more into an information the more often it was heared is known as **(illusiory) truth effect**.
We compute it as the of proportion of repetitions of the information $i$ $rep(i)$ to all receive messages $receives$: 

$T_{rep}(i) = rep(i)/receives$ 

The repetition score can only contribute positively to our trust and we have $T_{rep} \in [0,.. 1.0]$.

#### Argumentative Trust

The argumentative trust $T_{arg}(i)$ is computed from all trust scores $T(j)$ where _j_ has an argumentative impact (that is an immediate connection $j$->$i$) on _i_:

$T_{arg}(i) = \sum_{j\in impact(i)} infl(j,i)*T(j)$

Here, $infl(j,i)$ is defined by the type of edge $j$->$i$ as -1, -0.5, 0.5, 1 for strong attacks, attacks, supports and strong supports, respectively.

#### Initial Trust

The initial trust into an information _i_ could be assigned individually to each information. In this analysis module, it is currently evaluated in **four scenarios** that distinguish between the LLM _LLM_ and all other conversation partners _P_:

- a) trust all completely ($T_{init}(P) = 1$; $T_{init}(LLM)=1$)
- b) trust the LLM less ($T_{init}(P) = 1$; $T_{init}(LLM)=0.5$)
- c) trust the LLM more than others ($T_{init}(P) = 0.5$; $T_{init}(LLM)=1$)
- d) limit trust into all ($T_{init}(P) = 0.5$; $T_{init}(LLM)=0.5$)

We write $T_{init}(P)$ for { $T_{init}(i) | i$ from $p \in P$} and $T_{init}(LLM)$ for { $T_{init}(i) | i$ from $LLM$}.

### Further Analysis

Further analysis enables an additonal analysis that provides statistics and graphics about the differences between the felt and the calculated trust. This analysis is executed by a python script. Further info can be seen in [keml.py-analysis](src/main/java/keml/analysis/py/README.md).


## License
The license of this project is that of the [group](https://github.com/keml-group).
