package Control;

import Model.DataBaseGood;
import Model.SudokuGenerator;

import java.sql.SQLException;

public class Controller {
    /*
    A controller class according to the MVC architectural pattern that divides an application into three parts
    The controller responds to the user input and performs interactions on the data model objects.
    The controller receives the input, optionally validates it and then passes the input to the model.
     */
    private static DataBaseGood dbconCntl = DataBaseGood.getInstance();
    public SudokuGenerator sdg1;
    public SudokuGenerator CreateSudoku(int K)
    {
        this.sdg1 = new SudokuGenerator(K);
        return this.sdg1;
    }
    public int RegisterUser(String username, String firstname, String lastname, String password, String email, String country) {
        /*
        Register user in the database of the server using sql keywords ( implemented in the database class ).

        Args:
        String username  : the name that you want to call your future user.
        String firstname : your first name.
        String lastname  : your last name.
        String password  : your future user password.
        String email     : your email
        String country   : your country

        Yields:
        int              : 0 - worked, 1 - didn't worked

        Examples:

        >>> RegisterUser( coolguy, omer, sassoni, 1234, o@g.com, israel )

        -> in the data base :

        username  |  firstname  | lastname  |  password  |   email    |  country  |  score  |     admin
                  |             |           |            |            |           |         |
        coolguy   |    omer     |  sasoni   |    1234    |  o@g.com   |   israel  |    0    |  NormalUser
                  |             |           |            |            |           |         |

        */
        try {
            return dbconCntl.registerUser(username,firstname,lastname,password,email,country);
        } catch (SQLException e) {
            e.printStackTrace();
            return 1; // didn't work
        }
    }
    public boolean loginUser(String Username, String password) {
        /*
        Check if exists in the database username with the password that given
        in the parameters using sql keywords ( implemented in the database class ).

        Args:
        String username  : the name of the user.
        String password  : your future user password.

        Yields:
        boolean          : true - exist, false - not exist

        Examples:

        >>> LoginUser( coolguy, 1234 )

        with the data base :

        username  |  firstname  | lastname  |  password  |   email    |  country  |  score  |     admin
                  |             |           |            |            |           |         |
        coolguy   |    omer     |  sasoni   |    1234    |  o@g.com   |   israel  |    0    |  NormalUser
                  |             |           |            |            |           |         |


        return           : true
        */
        return dbconCntl.loginUser(Username, password);
    }

    public void LoginSession(String session, String username) throws SQLException {
        /*
        Insert into the session database the session and the username given in the parameter
        using sql keywords ( implemented in the database class ).

        Args:
        String username  : the name of the user.
        String session   : the session of the connection
        Yields:
        void             : insert this parameters into one row in the session database

        Examples:

        >>> LoginSession(ab@cd, coolguy)

        -> in the data base

        session     |     username
                    |
         ab@cd      |     coolguy
                    |
        */

        dbconCntl.loginSession(session, username);
    }

    public void UpdateLoginSession(String session, String username) {
        /*
        Update the session in the session database to the session and the username given in the parameter
        using sql keywords ( implemented in the database class ).

        Args:
        String username  : the name of the user.
        String session   : the session of the connection
        Yields:
        void             : update the session to this parameters.

        Examples:

        >>> UpdateLoginSession(12@34, coolguy)

        in this database :

        session     |     username
                    |
         ab@cd      |     coolguy
                    |

        will make it be  :

        session     |     username
                    |
         12@34      |     coolguy
                    |
        */
        dbconCntl.updateLoginSession(session, username);
    }
    public void MakeAdmin(String username) {
        /*
        An admin only method which make a NormalUser to be an Admin in the database
        using sql keywords ( implemented in the database class ).

        Args:
        String username  : the name of the user.
        Yields:
        void             : make admin NormalUser

        Examples:

        >>> MakeAdmin(coolguy)

        in this database :

        username  |  firstname  | lastname  |  password  |   email    |  country  |  score  |     admin
                  |             |           |            |            |           |         |
        coolguy   |    omer     |  sasoni   |    1234    |  o@g.com   |   israel  |    0    |  NormalUser
                  |             |           |            |            |           |         |

        will make it     :

        username  |  firstname |  lastname  |  password |    email    |  country  |  score  |     admin
                  |            |            |           |             |           |         |
        coolguy   |    omer    |   sasoni   |    1234   |   o@g.com   |   israel  |    0    |     Admin
                  |            |            |           |             |           |         |



        */

        dbconCntl.makeAdmin(username);
    }
    public void ZeroScore(String username) {
        /*
        An admin only method which zero the score to a user in the database
        using sql keywords ( implemented in the database class ).

        Args:
        String username  : the name of the user.
        Yields:
        void             : zero score a user.

        Examples:

        >>> ZeroScore(coolguy)

        in this database :

        username  |  firstname  | lastname  |  password  |   email    |  country  |  score  |     admin
                  |             |           |            |            |           |         |
        coolguy   |    omer     |  sasoni   |    1234    |  o@g.com   |   israel  |    17   |  NormalUser
                  |             |           |            |            |           |         |
        will make it     :

        username  |  firstname |  lastname  |  password |    email    |  country  |  score  |     admin
                  |            |            |           |             |           |         |
        coolguy   |    omer    |   sasoni   |    1234   |   o@g.com   |   israel  |    0    |   NormalUser
                  |            |            |           |             |           |         |

        */

        dbconCntl.zeroScore(username);
    }


    public void DeleteSession(String session) {
        /*
        Delete a session from the session database
        using sql keywords ( implemented in the database class ).

        Args:
        String session   : the session of the connection
        Yields:
        void             : delete the session from the session database

        Examples:

        >>> DeleteSession(12@34)

        in this database :

        session    |      username
                   |
         12@34     |      coolguy
                   |

        will make it     :

        session    |      username

        */


        dbconCntl.deleteSession(session);
    }

    public String DeleteUser(String username) {
        /*
        An admin only method which delete user in the database
        using sql keywords ( implemented in the database class ).

        Args:
        String username  : the name of the user you wish to delete.
        Yields:
        String            : succeed or failed

        Examples:
        >>> DeleteUser(coolguy)
        in this database :

        username  |  firstname |  lastname  |  password  |   email   |   country   | score  |     admin
                  |            |            |            |           |             |        |
        coolguy   |    omer    |   sasoni   |    1234    |  o@g.com  |    israel   |   17   |  NormalUser
                  |            |            |            |           |             |        |

        will make it     :

        username  |  firstname |  lastname  |  password  |   email   |   country   | score  |     admin
                  |            |            |            |           |             |        |
                  |            |            |            |           |             |        |

        */
        return dbconCntl.deleteUser(username);
    }

    public String UsernameBySession(String session2) {
        /*
        Return username by giving a session
        using sql keywords ( implemented in the database class ).

        Args:
        String session   : the session of the connection
        Yields:
        String           : return the username ( or false if failed ).

        Examples:

        >>> UsernameBySession(ab@cd)

        in this database :

        session     |     username
                    |
         ab@cd      |     coolguy
                    |

        return          : coolguy
        */
        return dbconCntl.usernameBySession(session2);
    }

    public String[] LoadUser(String username) {
        /*
        Return the information about an username
        using sql keywords ( implemented in the database class ).

        Args:
        String username  : the name of the user you wish to delete.
        Yields:
        String[]         : array of the information

        Examples:
        >>> LoadUser(coolguy)
        in this database :

        username  |  firstname |  lastname  |  password  |   email   |   country   | score  |     admin
                  |            |            |            |           |             |        |
        coolguy   |    omer    |   sasoni   |    1234    |  o@g.com  |    israel   |   17   |  NormalUser
                  |            |            |            |           |             |        |

        will return     :

        [ omer, sasoni, 1234, o@g.com, israel, 17, NormalUser ]
        */
        return dbconCntl.loadUser(username);
    }

    public void UpdateScore(String username, int K) {
        /*
        Add to score of giving username - K where K is a parameter
        using sql keywords ( implemented in the database class ).

        Args:
        String username  : the name of the user you wish to delete.
        int K            : the number you wish to add to score.
        Yields:
        void             : add K to username score

        Examples:
        >>> LoadUser(coolguy, 13)
        in this database :

        username  |  firstname |  lastname  |  password  |   email   |   country   | score  |     admin
                  |            |            |            |           |             |        |
        coolguy   |    omer    |   sasoni   |    1234    |  o@g.com  |    israel   |   17   |  NormalUser
                  |            |            |            |           |             |        |

        will make it     :

        username  |  firstname |  lastname  |  password |    email    |  country  |  score  |     admin
                  |            |            |           |             |           |         |
        coolguy   |    omer    |   sasoni   |    1234   |   o@g.com   |   israel  |    30   |   NormalUser
                  |            |            |           |             |           |         |

        */
        dbconCntl.updateScore(username, K);
    }
    public String[] GetAllUsers() {
        /*
        Return all the users in the users database
        using sql keywords ( implemented in the database class ).

        Args:
        None
        int K            : the number you wish to add to score.
        Yields:
        String[]         : array with the users.

        Examples:
        >>> GetAllUsers()
        in this database :

        username   |  firstname |  lastname  |  password  |   email   |   country   | score  |     admin
                   |            |            |            |           |             |        |
        coolguy1   |    omer    |   sasoni   |    1234    |  o@g.com  |    israel   |   17   |  NormalUser
                   |            |            |            |           |             |        |
        coolguy2   |    omer    |   sasoni   |    1234    |  o@g.com  |    israel   |   17   |  NormalUser
                   |            |            |            |           |             |        |

        will return     :

        [ coolguy1, coolguy2 ]

        */
        return dbconCntl.getAllUsers();
    }
    public void getTopTen(int[] returnscores, String[] returnplayers)
    {
        /*
        Return the top ten users ( ordered by sccore )
        using sql keywords ( implemented in the database class ).

        Args:
        int[] returnscores     : the array which will hold the top ten scores.
        String[] returnplayers : the array which will hold the top ten players.
        Yields:
        void                   : put the best users in the returnplayers array
                                 and the best scores in the returnscores array

        Examples:
        >>> int[] returnscores     = new int[10];
        >>> String[] returnplayers = new String[10];
        >>> getTopTen(returnscores, returnplayers)
        in this database :

        username    |  firstname |  lastname  |  password  |   email   |   country   | score  |     admin
                    |            |            |            |           |             |        |
        coolguy1    |    omer    |   sasoni   |    1234    |  o@g.com  |    israel   |   30   |  NormalUser
                    |            |            |            |           |             |        |
        coolguy2    |    omer    |   sasoni   |    1234    |  o@g.com  |    israel   |   12   |  NormalUser
                    |            |            |            |           |             |        |
        coolguy3    |    omer    |   sasoni   |    1234    |  o@g.com  |    israel   |   50   |  NormalUser
                    |            |            |            |           |             |        |

        will return     :

        returnplayers   : [ coolguy3, coolguy1, coolguy2 ]
        returnscores    : [ 50, 30, 12 ]

        */
        dbconCntl.getTopTen(returnscores,returnplayers);
    }
}