# Cricket

#### To play the game you need to run the file "CricketApplication.java".
## 1. Match Controller
#### Description:
This controller class provides RESTful endpoints for handling CRUD operations for the Matches entity.

#### Dependencies:
This controller relies on the MatchesService class to perform database operations.

#### Endpoints:

Add Match:

1. Endpoint: POST /addMatches
   Description: Adds a new match to the database.
   Parameters:

matches: The Matches object to be added.
Returns: void
Get All Matches:

2. Endpoint: GET /matches
   Description: Retrieves all matches from the database.
   Parameters: None
   Returns: A list of Matches objects.
   Get Match By ID:

3. Endpoint: GET /matches/{matchId}
   Description: Retrieves a match from the database based on its ID.
   Parameters:
   matchId: The ID of the match to be retrieved.
   Returns: The Matches object with the given ID or null if not found.
   Remove Matches:

4. Endpoint: DELETE /matches
   Description: Deletes all matches from the database.
   Parameters: None
   Returns: A message indicating the number of matches deleted.
   Remove Match By ID:

5. Endpoint: DELETE /matches/{matchId}
   Description: Deletes a match from the database based on its ID.
   Parameters:
   matchId: The ID of the match to be deleted.
   Returns: A message indicating whether the deletion was successful or not.
   Note:

This controller relies on Spring's @Autowired annotation to inject an instance of MatchesService into the class.
The Matches class is assumed to be a domain entity with appropriate properties and methods.

## 2. PlayersController API documentation

#### This API provides endpoints for managing players and their records.

### Players

#### Represents a player in the system.

Properties
* playerId (String): Unique identifier for the player.
* playerName (String): The name of the player.
* teamName (String): The name of the team the player belongs to.
* highScore (int): The highest score achieved by the player in any match.
* playerRecordList (List of PlayerRecord objects): The list of all the player's match records.

### PlayerRecord

#### Represents a player's record in a specific match.

Properties
- matchId (String): Unique identifier for the match.
- runsScoredInTheMatch (int): The total number of runs scored by the player in the match.
- numberOfFours (int): The number of times the player hit a four in the match.
- numberOfSixes (int): The number of times the player hit a six in the match.
- numberOfBallsFaced (int): The number of balls faced by the player in the match.

### Endpoints

POST /addPlayer
Adds a new player to the system.

#### Request Body
player (Players object): The player to be added.

***GET*** /findPlayerById/{playerId}
Finds a player by their ID.

#### Path Parameters
playerId (String): The ID of the player to find.

***GET*** /findByPlayerName/{playerName}
Finds all players with a given name.

#### Path Parameters
playerName (String): The name of the player(s) to find.

***GET*** /findAllByTeamName/{teamName}
Finds all players in a given team.

#### Path Parameters
teamName (String): The name of the team to find players for.

***GET*** /findByPlayerNameAndTeamName/{playerName}/{teamName}
Finds a player with a given name in a given team.

#### Path Parameters
playerName (String): The name of the player to find.
teamName (String): The name of the team the player belongs to.

***PUT*** /updatePlayer
Updates an existing player in the system.

#### Request Body
player (Players object): The updated player.

## 3. StartMatchController documentation
This is a controller class named StartMatchController. It has a single method named set which is mapped to a POST request to the "/start" endpoint. The method takes in a parameter of type GameDataDTO, which is a data transfer object that contains the data required to start a match.

In the set method, if the size of team1 or team2 in the GameDataDTO object is less than 11, then default player names are generated for the remaining slots. The default player names are generated in the format "playerX", where X is the slot number starting from 1.

Finally, the start method of the StartMatchService object is called with the GameDataDTO object as the parameter, and the result is returned as a string.