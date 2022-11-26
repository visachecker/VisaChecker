# [Check us out!](https://35.157.137.195/)
[![VisaChecker.CD](https://github.com/visachecker/VisaChecker/actions/workflows/cd.yml/badge.svg)](https://github.com/visachecker/VisaChecker/actions/workflows/cd.yml)
[![VisaChecker.CI](https://github.com/visachecker/VisaChecker/actions/workflows/ci.yml/badge.svg)](https://github.com/visachecker/VisaChecker/actions/workflows/ci.yml)
# VisaChecker Development Process

## Status updates
### Team workflow
Status updates are to be provided asynchronously in respective issue's comments.

Once a week (on Sunday) team has a meeting, where: 
 * Current project status is discussed 
 * Workflow/development ideas are refined (retrospective)
 * Next development iteration is planned (planning)
 
## Development
### Branching
* Branch names must contain issue number in the beginning. No direct pushes to `main` branch are allowed. Details: https://www.atlassian.com/git/tutorials/comparing-workflows/feature-branch-workflow
* Updates to `README.md` can go through simpler commit process - straight commit to `main`.


## Documentation
## Installation
* First, be sure that you have [Node.js](https://nodejs.org/en/) installed.
* After cloning project run ```npm install``` in the folder **VisaChecker.UI**.
* **VisaChecker.Server** is using **Gradle**, if you open the project as monorepo, Gradle will run automatically, but if you want to run some of the Gradle tasks, you need to open **VisaChecker.Server** as a separate project.
* To launch frontend execute the command ```npm run``` in **VisaChecker.UI** folder.
* To run the backend launch Main.java in **VisaChecker.Server** folder as you normally do with java programs.
## Architecture draft
### Diagram
https://miro.com/app/board/uXjVPAext0w=/
