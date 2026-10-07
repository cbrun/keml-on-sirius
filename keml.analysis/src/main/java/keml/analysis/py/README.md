# KEML.PY Analysis

This project further analyses the files created by [KEML Analysis](https://github.com/keml-group/keml.analysis). For each of the four scenarios it provides:

1) Signed differences between feltTrustImmediately/feltTrustAfterwards and the calculated trust for each information
2) Mean, variance and standard deviation of the absolute differences
3) Graphical depiction of the signed differences with the help of histograms

## Installation

The script runs with Python 3. Additionally it requires the installation of the packages numpy, matplotlib and openpyxl. To install them you will need the Python package installer pip which usually comes with Python installations by default. Run "pip install -r requirements.txt" in your command line to install said packages.

## Running

Running "python3 main.py \<dir_name\> \<path\>" in your command line is sufficient. Here, \<dir_name\> is the name of the folder that resulted when running KEML Analysis for a given keml-json file, while \<path\> is the path to this folder. If only the first argument is given the script uses the default path "../keml.sample/introductoryExamples/analysis" assuming the repositories for KEML.PY Analysis and [KEML Sample](https://github.com/keml-group/keml.sample) lie in the same directory. If no arguments are given a TypeError is raised. 

## Output

In the case of the numerical calculations, the script writes its output directly into the respective files. For the generated histograms a folder "stats" is created within the folder created by [KEML Analysis](https://github.com/keml-group/keml.analysis). In this folder for each analysis file and its four scenarios the histograms depicting the signed differences are saved.

## License
The license of this project is that of the [group](https://github.com/keml-group).