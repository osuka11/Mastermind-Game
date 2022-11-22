

fun evaluateGuess(secret: String, guess:String):Evaluation{
    val positionsRight = getRightsPositions(secret, guess)
    val positionsWrong = getWrongPositions(secret, guess)

    return Evaluation(positionsRight, positionsWrong)
}
private fun getRightsPositions(secret:String, guess: String): Int {
    var counterPositions = 0        //How many letters are guessed
    for((index, element) in secret.withIndex()){
        if(element == guess[index]){
            counterPositions++
        }
    }
    return counterPositions
}

private fun getWrongPositions(secret: String, guess: String):Int{
    var letters = 0     //Counter about how many letters are in wrong position
    var newSecret = ""
    var newGuess = ""

    //val secretList = secret.toMutableList()
    //Look for what letter of Guess are not in Secret
    for((index, element) in secret.withIndex()){
        if(element != guess[index]){
            newSecret += secret[index]  //Create a string with letters of secret that are not in Guess
            newGuess += guess[index]    //Create a string with letters of Guess that are not in Secret
        }
    }
    println("New Guess:$newGuess, New Secret:$newSecret")

    val charsEvaluation = mutableListOf<Char>()     //List of Chars to add the letters with the wrong position in guess
    if(newSecret.isNotEmpty()){
        for (i in guess.indices){
            val letter = guess[i]       //Evaluated every letter of guess, if the evaluated is already evaluated, continue with next one
            if(!charsEvaluation.contains(letter)){
                val lettersInSecret = countHowMany(newSecret, letter)
                val lettersInGuess = countHowMany(newGuess, letter)
                println("Letter in Guess : $lettersInGuess, Letter in Secret: $lettersInSecret")
                letters += if ( lettersInSecret == lettersInGuess || lettersInSecret > lettersInGuess) lettersInGuess else lettersInSecret
                charsEvaluation.add(letter)
            }

        }
    }
    return letters

}

private fun countHowMany(chain: String, letter: Char): Int {
    var counterPositions = 0
    for (element in chain){
        if(element == letter){
            counterPositions++
            println("$element and $letter for $chain")
        }
    }
    return counterPositions

}
