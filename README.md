# paragon-automation

## Why was this project  created?
This project is created as part of Paragon Website testing by Gojilabs LLC.

## How to get started?
Start by forking the repository.


## What are the components of the framework?
Below are the tech-stack used:

**Maven**
- manage dependencies and plugins, integrate with CI/CD tools

**TestNG**
- test configurations in testng.xml, annotations, data provider, listeners - e.g. capturing screenshot on failure

**Properties file**
- maintain global properties

**POM**
- test pages, page objects

**Tests**
- separate driver config - uses WebDriverManager
- separate utilities class
- multi data (using testng data provider) extendable horizontally and vertically
- multi browser (using properties)

**Log4j2**
- for logging (both console and file)

**GIT**
- integration options

**Jenkins**
- integration options

**Reports**
- native testng report and surefire-reports included
- will be extended to Allure reports

