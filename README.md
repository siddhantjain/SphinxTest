# SphinxTest
A program to generate subtitles for an audio file automatically

##About

A simple java program that does the preliminary work of putting together CMU Sphinx libraries to achieve a meaningful goal.
The objective of creating this project was to serve as a reference for anyone who might want to get started with Sphinx. 
Besides, subtitling an audio file seemed like a basic utility for which a ready-to-use example should have been present, alas, i could not find anything easily.


##How to use
It's pretty simple. Just download the entire project and run the main.java file. You will be asked to give path to the audio file you want
to transcribe. After which, you should just sit back and relax. There will be flurry of log messages. The transcription process is a little slow.
Once the program is done transcribing, you can find your srt file in project folder with the name same as your audio file.

##Tips

* I have included a demo file, grammar.wav to help you test the program
* If you are not happpy with the accuracy you are getting, you should definitely refer to [this FAQ](http://cmusphinx.sourceforge.net/wiki/faq). It will help a lot.
