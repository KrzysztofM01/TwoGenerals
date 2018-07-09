# Two Generals

A turn based strategy card game for two players, made in JavaFX technology. Application uses Socket/Server Socket for client connection and multiple threads to support game logic, graphics and network at the same time. 

The game uses database to store all cards present in game, players with their logins/password/card decks and allows users to suggest new cards, whereas admins to accept/decline them. Database is hosted on db4free.net to allow everyone to test the application.


How the game works:
1) You can choose a deck which can consist of maximum 25 cards or play with the standard one.
2) There are two types of cards - Battle Cards and Special Cards. Battle Cards are stronger, but do not possess special abilities, whereas Special Cards will cause a special effect when played.
3) All cards have two statistics: in red/blue flag their power, in purple flag their action points needed to use the card.
4) There are 3 front lines on the board, each player can play card on one of the front lines, increasing that front line power. Player can also once per turn attack on front line.
5) Triggering attack on front line will cause the game to calculate the difference between player and opponents front line power, and decrease the front line hit points of player that has less power by the difference amount.
6) If the enemy front line hit points is equal to 0, the losing player will lose health equal to the difference amount.
7) To win the game you have to bring down opponents health points to 0.



<details> 
  <summary>Images of application:</summary>

Login and Main Panel:

![alt text](https://github.com/KrzysztofM01/TwoGenerals/blob/master/readme_images/loginPanel.png)
![alt text](https://github.com/KrzysztofM01/TwoGenerals/blob/master/readme_images/mainPanel.png)

Preview cards/Choose your deck Panel:

![alt text](https://github.com/KrzysztofM01/TwoGenerals/blob/master/readme_images/previewCardsPanel.png)

Suggest Cards Panel:

![alt text](https://github.com/KrzysztofM01/TwoGenerals/blob/master/readme_images/suggestCardPanel_1.png)
![alt text](https://github.com/KrzysztofM01/TwoGenerals/blob/master/readme_images/suggestCardPanel_2.png)

Start Game/Networt Panel:

![alt text](https://github.com/KrzysztofM01/TwoGenerals/blob/master/readme_images/NetworkPanel.png)

Game Board:

![alt text](https://github.com/KrzysztofM01/TwoGenerals/blob/master/readme_images/gameBoard_1.png)
![alt text](https://github.com/KrzysztofM01/TwoGenerals/blob/master/readme_images/gameBoard_2.png)
![alt text](https://github.com/KrzysztofM01/TwoGenerals/blob/master/readme_images/gameBoard_3.png)
![alt text](https://github.com/KrzysztofM01/TwoGenerals/blob/master/readme_images/gameBoard_4.png)
![alt text](https://github.com/KrzysztofM01/TwoGenerals/blob/master/readme_images/gameBoard_5.png)
</details>
