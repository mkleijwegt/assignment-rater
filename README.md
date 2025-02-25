assignment-rater
=======================

This program can be used to automatically rate work of students that turned in their work in a GitHub classroom assignment.

Features
---------------------

* Supports assignment json files where instructions can be provided what classroom assignment to rate and how to rate it.
* Automatically downloads GitHub repositories from a classroom assignment (per student).
* Generates PDF files containing feedback as provided by the AI model.
* Also possible to rate a single repository (no classroom needed). See AssignmentRaterSingleRepositoryTest.

Developer Guides
---------------------
To get this up and running the easiest way is to download Eclipse from https://www.eclipse.org/downloads/ and open up the project. From there:
* Install ollama server https://ollama.com/.
* Download an AI model in ollama (use this command in Microsoft Powershell: ollama run qwen2.5-coder:7b).
* Setup a key that gains you access to your GitHub organisation so you can see all the repositories in the organisation.
* Go to https://github.com/settings/profile and click on 'Developer settings'.
* Click on 'Personal access tokens' and then 'Fine-grained tokens'.
* Click on 'Generate new token' and make sure the resource owner is your organisation.
* Make sure your have access to all repositories and set the permission 'Contents' to 'Read-only' access.
* Copy the generated token to a file (.github) in your home directory (Windows this is c:\users\{your_user_name}\.github
* Make sure it has two lines: 
login={your_organisation_name} 
oauth={your_token}
* WARNING: Do not ever share your GitHub access token with anyone! The program only uses it to connect to GitHub and is only kept in memory for the duration of the program.
* Setup a json file in the assignment-files directory with your assignment details (there are a few examples present).
* Run AssignmentRaterFromFileTest as a JUnit test to make it rate a classroom assignment.
* The resulting PDF files can be found in the project 'result' directory.

License
---------------------
No license rightnow. Send me a message if you tried it and tell me what you think.
