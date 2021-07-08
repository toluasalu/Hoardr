## Contribution Guide

### How To Begin Contribution
- Fork this repository
- Copy the link to your forked repo
- Open up Android Studio
- Create a new project from version control using the copied link
- Set upstream remote pointing to the repo on Zuri's account e.g. git remote add upstream https://github.com/zuri-training/hoardr-mobile-95-new.git
- Pull from the develop branch of upstream to load any new changes made to the upstream repo e.g git pull upstream develop
- Create a new branch for the task you are doing e.g. git checkout -b login
- After making changes, do git add .
- Commit your changes with a descriptive commit message e.g. git commit -m "implemented the google auth signing using firebase"
- Pull from upstream to make sure there are no conflicts e.g. git pull upstream develop
- Push changes to your new branch e.g. git push origin your-current-branch-name
- Create a pull request to the develop branch

### Rules and Guidelines to be used during contribution
- Follow the correct naming ideology of files and id e.g fragment_home.xml not home_fragment, @+id/text_name not @+id/name or something like that
- All Kotlin classes concerning the UI must be placed in the ui package inside another package with the name of the screen e.g Onboarding Screen Controller and ViewModel will be stored under the ui/onboarding package
- Make sure you pull from upstream and fix conflicts before making a PR if notit will be rejected

That's all for now