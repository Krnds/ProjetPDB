# PDB Project 

[![PDB](https://cdn.rcsb.org/rcsb-pdb/v2/common/images/rcsb_logo.png)](https://www.rcsb.org/)

### A Java command line tool for fetching protein structures, calculating distances between residues, finding lowest cut-off distances and displaying interaction zones automatically.

[![GitHub license](https://img.shields.io/github/license/Naereen/StrapDown.js.svg)](https://github.com/Naereen/StrapDown.js/blob/master/LICENSE)
<p align="center">
  <img src="https://cdn.rcsb.org/pdb101/motm/images/mom128_3bes.jpg" width="230" height="300"/>
</p>
<p align ="center"><i>This is my final year project of my bioinformatics bachelor degree</i></p>



## INSTRUCTIONS

- **1.** Find all molecular complexes from the Protein Data Bank (PDB), i.e. 2 or more molecules.

- **2.** Calculate the distances between **2 residues of 2 molecules** from a PDB molecular complex.

- **3.** Give **all the residues within a threshold distance** given by the user.

- **4.** Test on 10 given **cytokine/cellular receptor** complexes. Find out what are the shortest distances and define empirically "**interaction zones**" between atoms of different molecules. 
This will define a **cutoff distance** used below.

- **5** Define an **interaction zone between 2 proteins** of a molecular complex i.e. all the residues located at a lower distance than the cutoff one. Apply this to the 10 cytokines complexes and represent all interaction zones by coloring distinctively all the
residues located in that zone.  

- **6.** Define for a **cytokine/receptor complex all the interaction zones containing the nearest residues** and color them.


## INFO

This tool gather PDB structures by their mmCIF format (key/value dictionary). It parses all useful info for calculating atomic distances (taken from alpha carbons), lowest distances between structures. 
**Interaction zone** is a 3D space in which all colored atoms have a distance lower than **8 Angstroms** between each entity/polymer (protein-protein/protein-molecule etc.)

## USAGE

This is a command line tool launched with the main class  `App.java`
A menu of 5 choices is displayed :

- Fetch all molecular complexes of 2 entities from the PDB API
- Calculate distances between 2 random residues taken from each molecule of a complex
- Give all the minimal distances found between atoms of each molecule
- Choose a structure or a cytokine/receptor complex and display interaction zones with [Jmol](http://jmol.sourceforge.net/)

## RESULT
![Example 1](https://github.com/Krnds/ProjetPDB/blob/master/doc/example1.png)

The first protein or entity is in green-ish blue, the second is in yellow and the interaction zone between the two is colored in red.

![Example 2](https://github.com/Krnds/ProjetPDB/blob/master/doc/example2.png)


## TODO IMPROVEMENTS

- [ ] Add language menu option
- [ ] Add menu in english
- [ ] Clean up info printing on screen
- [ ] Add new feature : more than 2 molecules handled
- [ ] Resolve bug to force ending of program when jmol is launched
- [ ] Add option for calculating distances with CNO atoms
- [ ] Add option to color differently all atoms within their chain
- [ ] Choose a random or chosen structure and display interaction zones


## AUTHOR
Karine Dias

## LICENCE
MIT
