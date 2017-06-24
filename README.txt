This is the LOGTAILER app in spring mvc.

The app creates 3 log files and writes the same data in all of them.
A thread is spawned when the app is loaded and it runs in the background and keeps on logging in the files.
Thread logs into the file every 5 seconds.
On click of button on webpage the respective log file is opened and is rendered on the screen, it is refreshed
every 1 second.
Log files are located inside the catalina.home folder.

Do a maven build and deploy the build, start the server to see the result.