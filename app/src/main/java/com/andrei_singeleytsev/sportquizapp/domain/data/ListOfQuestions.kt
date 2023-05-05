package com.andrei_singeleytsev.sportquizapp.domain.data

import com.andrei_singeleytsev.sportquizapp.domain.models.QuestionItem
import com.andrei_singeleytsev.sportquizapp.domain.utils.Categories

sealed class ListOfQuestions {
    object Football : ListOfQuestions() {
        val category = Categories.FOOTBALL
        val array = arrayOf(
            QuestionItem(
                "What was the score in the Euro 2012 final?",
                listOf(
                    "4–0", "2–0", "3–0", "5–0"
                )
            ),
            QuestionItem(
                "Who won the Man of the Match award in the 2014 World Cup final?",
                listOf(
                    "Mario Goetze", "Sergio Aguero", "Lionel Messi", "Bastian Schweinsteiger"
                )
            ),
            QuestionItem(
                "Against which country did Wayne Rooney break the England goalscoring record?",
                listOf(
                    "Switzerland", "San Marino", "Lithuania", "Slovenia"
                )
            ),
            QuestionItem(
                "After losing a key player in the first game, which team went onto the semi-finals of Euro 2020?",
                listOf(
                    "Denmark", "Spain", "Wales", "England"
                )
            ),
            QuestionItem(
                "Who is the current top scorer in the UEFA Champions League?",
                listOf(
                    "Alan Shearer", "Thierry Henry", "Robert Lewandowski"
                )
            ),
        )
    }

    object Basketball : ListOfQuestions() {
        val category = Categories.BASKETBALL
        val array = arrayOf(
            QuestionItem(
                "Which team won the NBA Finals in 2021?",
                listOf(
                    "Milwaukee Bucks", "Phoenix Suns", "Los Angeles Lakers", "Brooklyn Nets"
                )
            ),
                    QuestionItem(
                    "Which player won the NBA Finals MVP award in 2021?",
            listOf(
                "Giannis Antetokounmpo", "Chris Paul", "LeBron James", "Devin Booker"
            )
        ),
        QuestionItem(
        "Who holds the record for the most points scored in an NBA game?",
        listOf(
        "Wilt Chamberlain", "Kobe Bryant", "Michael Jordan", "LeBron James"
        )
        ),
        QuestionItem(
        "Which team holds the record for the most NBA championships?",
        listOf(
        "Boston Celtics", "Los Angeles Lakers", "Chicago Bulls", "Golden State Warriors"
        )
        ),
        QuestionItem(
        "Who is the all-time leading scorer in NBA history?",
        listOf(
        "Kareem Abdul-Jabbar", "Kobe Bryant", "LeBron James", "Michael Jordan"
        )
        )
        )
    }
    object Hockey : ListOfQuestions() {
        val category = Categories.HOCKEY
        val array = arrayOf(
            QuestionItem(
                "Which team won the Stanley Cup in 2021?",
                listOf(
                    "Tampa Bay Lightning", "Montreal Canadiens", "New York Islanders", "Vegas Golden Knights"
                )
            ),
                    QuestionItem(
                        "Who holds the NHL record for the most career goals?",
                        listOf(
                            "Wayne Gretzky", "Mario Lemieux", "Jaromir Jagr", "Brett Hull"
                        )
                    ),
                    QuestionItem(
                    "Which team holds the record for the longest winning streak in NHL history?",
            listOf(
                "Pittsburgh Penguins", "Montreal Canadiens", "Chicago Blackhawks", "Boston Bruins"
            )
        ),
        QuestionItem(
        "Who won the Hart Trophy for the NHL's Most Valuable Player in the 2020-21 season?",
        listOf(
        "Connor McDavid", "Leon Draisaitl", "Nathan MacKinnon", "Sidney Crosby"
        )
        ),
        QuestionItem(
        "Which team holds the record for the most consecutive Stanley Cup championships?",
        listOf(
        "Montreal Canadiens", "Toronto Maple Leafs", "Detroit Red Wings", "New York Islanders"
        )
        ),
        )
    }
    object Volleyball : ListOfQuestions() {
        val category = Categories.VOLLEYBALL
        val array = arrayOf(
            QuestionItem(
                "Who won the men's volleyball gold medal at the 2021 Olympics?",
                listOf(
                    "Brazil", "Italy", "Argentina", "Poland"
                )
            ),
            QuestionItem(
                "Who is the all-time leading scorer in men's FIVB Volleyball World Championship history?",
                listOf(
                    "Ivan Zaytsev", "György Grozer", "Goran Vujevic", "Yasser Abdelrahman"
                )
            )
        )
    }
    object Martial_Arts : ListOfQuestions() {
        val category = Categories.MARTIAL_ARTS
        val array = arrayOf(
            QuestionItem(
                "Who is the current UFC lightweight champion?",
                listOf(
                    "Charles Oliveira", "Khabib Nurmagomedov", "Conor McGregor", "Dustin Poirier"
                )
            ),
            QuestionItem(
                "Which martial art is known as the 'gentle way'?",
                listOf(
                    "Judo", "Karate", "Tae Kwon Do", "Muay Thai"
                )
            ),
            QuestionItem(
                "Who was the first UFC women's bantamweight champion?",
                listOf(
                    "Ronda Rousey", "Holly Holm", "Amanda Nunes", "Miesha Tate"
                )
            )
        )
    }object Tennis : ListOfQuestions() {
        val category = Categories.TENNIS
        val array = arrayOf(
            QuestionItem(
                "Who won the men's singles title at the 2021 Wimbledon Championships?",
                listOf(
                    "Novak Djokovic", "Roger Federer", "Rafael Nadal", "Stefanos Tsitsipas"
                )
            ),
            QuestionItem(
                "Who won the women's singles title at the 2021 Australian Open?",
                listOf(
                    "Naomi Osaka", "Serena Williams", "Simona Halep", "Jennifer Brady"
                )
            ),
            QuestionItem(
                "Which player has won the most WTA singles titles in history?",
                listOf(
                    "Martina Navratilova", "Serena Williams", "Steffi Graf", "Chris Evert"
                )
            ),
            QuestionItem(
                "Who won the men's singles gold medal at the 2021 Olympics?",
                listOf(
                    "Alexander Zverev", "Novak Djokovic", "Daniil Medvedev", "Stefanos Tsitsipas"
                )
            )
        )
    }
    object Skiing : ListOfQuestions() {
        val category = Categories.SKIING
        val array = arrayOf(
            QuestionItem(
                "Who is the most decorated Winter Olympian of all time with 15 medals?",
                listOf(
                    "Bjørn Dæhlie", "Ole Einar Bjørndalen", "Marit Bjørgen", "Simon Ammann"
                )
            ),

            QuestionItem(
                "Which country has won the most medals in alpine skiing at the Winter Olympics?",
                listOf(
                    "Austria", "United States", "Switzerland", "Norway"
                )
            ),

            QuestionItem(
                "Which discipline of skiing is known for its moguls and aerial jumps?",
                listOf(
                    "Freestyle skiing", "Nordic skiing", "Alpine skiing", "Ski jumping"
                )
            ),

            QuestionItem(
                "Which cross-country skier has won the most Olympic gold medals?",
                listOf(
                    "Marit Bjørgen", "Bjørn Dæhlie", "Charlotte Kalla", "Therese Johaug"
                )
            ),

            QuestionItem(
                "Who was the first skier to win the World Cup overall title four times in a row?",
                listOf(
                    "Pirmin Zurbriggen", "Ingemar Stenmark", "Luc Alphand", "Hermann Maier"
                )
            )
        )
    }
    object Baseball : ListOfQuestions() {
        val category = Categories.BASEBALL
        val array = arrayOf(
            QuestionItem(
                "Who currently holds the record for the most home runs hit in a single season?",
                listOf(
                    "Barry Bonds", "Mark McGwire", "Sammy Sosa", "Babe Ruth"
                )
            ),

            QuestionItem(
                "Which team won the most World Series championships in the 20th century?",
                listOf(
                    "New York Yankees", "Boston Red Sox", "St. Louis Cardinals", "Los Angeles Dodgers"
                )
            ),

            QuestionItem(
                "What is the term used to describe a batter who hits the ball and then runs around all four bases to score a run?",
                listOf(
                    "Home run", "Grand slam", "Bunt", "Single"
                )
            ),

            QuestionItem(
                "Which pitcher holds the record for the most strikeouts in a single season?",
                listOf(
                    "Nolan Ryan", "Randy Johnson", "Pedro Martinez", "Roger Clemens"
                )
            ),

            QuestionItem(
                "Which team won the first ever World Series in 1903?",
                listOf(
                    "Boston Americans (now Red Sox)", "Pittsburgh Pirates", "New York Giants", "Chicago Cubs"
                )
            )
        )
    }
    object Golf : ListOfQuestions() {
        val category = Categories.GOLF
        val array = arrayOf(
            QuestionItem(
                "Which golfer has won the most major championships?",
                listOf(
                    "Jack Nicklaus", "Tiger Woods", "Walter Hagen", "Ben Hogan"
                )
            ),

            QuestionItem(
                "What is the term used to describe a score of one stroke under par on a hole?",
                listOf(
                    "Birdie", "Eagle", "Bogey", "Par"
                )
            ),

            QuestionItem(
                "Which golf course is famous for hosting the Masters Tournament every year?",
                listOf(
                    "Augusta National", "Pebble Beach", "St. Andrews", "Bethpage Black"
                )
            ),

            QuestionItem(
                "Which country has won the most Ryder Cup tournaments?",
                listOf(
                    "United States", "Europe", "Great Britain and Ireland", "Australia"
                )
            ),

            QuestionItem(
                "Who is the only golfer to win all four major championships in a calendar year, a feat known as the 'Grand Slam'?",
                listOf(
                    "Bobby Jones", "Jack Nicklaus", "Tiger Woods", "Arnold Palmer"
                )
            )
        )
    }
    object Rugby : ListOfQuestions() {
        val category = Categories.RUGBY
        val array = arrayOf(
            QuestionItem(
                "Which country has won the most Rugby World Cup tournaments?",
                listOf(
                    "New Zealand", "Australia", "South Africa", "England"
                )
            ),

            QuestionItem(
                "What is the name of the international rugby union team representing the British Isles?",
                listOf(
                    "British and Irish Lions", "All Blacks", "Wallabies", "Springboks"
                )
            ),

            QuestionItem(
                "In what year was the first Rugby World Cup held?",
                listOf(
                    "1987", "1991", "1995", "1999"
                )
            ),

            QuestionItem(
                "What is the name of the annual rugby union competition involving England, France, Ireland, Italy, Scotland and Wales?",
                listOf(
                    "Six Nations Championship", "Rugby Championship", "Tri-Nations", "The Rugby Cup"
                )
            ),

            QuestionItem(
                "What is the term used to describe a try that is scored by a player who collects a kicked ball within their own in-goal area?",
                listOf(
                    "A 22 drop-out", "A scrum", "A penalty try", "A grubber"
                )
            )
        )
    }
    object Handball : ListOfQuestions() {
        val category = Categories.HANDBALL
        val array = arrayOf(
            QuestionItem(
                "Which country has won the most men's handball World Championships?",
                listOf(
                    "France", "Spain", "Germany", "Russia"
                )
            ),

            QuestionItem(
                "What is the name of the position in handball that is responsible for stopping the opposing team from scoring goals?",
                listOf(
                    "Goalkeeper", "Pivot", "Winger", "Centre back"
                )
            ),

            QuestionItem(
                "What is the maximum number of steps a player can take with the ball in handball?",
                listOf(
                    "Three", "Four", "Five", "Six"
                )
            ),

            QuestionItem(
                "What is the name of the annual club handball competition in Europe?",
                listOf(
                    "EHF Champions League", "World Handball Club Championship", "IHF Super Globe", "EHF European Cup"
                )
            ),

            QuestionItem(
                "What is the name of the handball move where a player jumps, shoots or passes the ball before landing with one foot and then landing with the other?",
                listOf(
                    "The jump shot", "The pivot", "The spin shot", "The lob"
                )
            )
        )
    }
    object Cycling : ListOfQuestions() {
        val category = Categories.CYCLING
        val array = arrayOf(
            QuestionItem(
                "Which cyclist won the 2021 Tour de France?",
                listOf(
                    "Tadej Pogacar", "Primoz Roglic", "Richard Carapaz", "Wout van Aert"
                )
            ),

            QuestionItem(
                "What is the name of the race that is considered to be the oldest one-day cycling event in the world?",
                listOf(
                    "Paris-Roubaix", "Tour of Flanders", "Milan-San Remo", "Liege-Bastogne-Liege"
                )
            ),

            QuestionItem(
                "Which country has won the most medals in Olympic track cycling?",
                listOf(
                    "Great Britain", "France", "Germany", "Netherlands"
                )
            ),

            QuestionItem(
                "What is the name of the cycling race that covers the entire circumference of the Earth's equator?",
                listOf(
                    "The Global Bicycle Race", "Around the World by Bicycle", "The Long Ride Home", "The Great Cycle Challenge"
                )
            ),

            QuestionItem(
                "Which cyclist won the most stages in the 2021 Giro d'Italia?",
                listOf(
                    "Peter Sagan", "Egan Bernal", "Simon Yates", "Caleb Ewan"
                )
            )
        )
    }

}
