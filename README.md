# Song Recognition Game
This project is a song recognition game in Java console. 
The app plays a song that his filepath is read from a text file that is used as a database. 
The app asks the user for the song's name. 
If the answer is correct, the user gets [100-(times passed in seconds * 2)] points.
If the answer is wrong or the user does not submit an answer in 50 seconds, the user does not get any points.
<br/><br/>
The game is played until the user gets 3 wrong answers or all the songs from the database were played.
Each song can be played once each game, and the console shows the song's name if the user get the wrong answer.
The top 5 scores are saved in the leaderboard, and the leaderboard is printed in the end of  each game.
Only the highest score of each user counts.
<br/><br/>
Single question function is in Music.java file.
Leaderboard functions are in Leaderboard.java file.
The game as a whole is in Game.java file.
