package dev.m3k.soofka

import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GameViewModel
@Inject constructor() : ViewModel() {
    private val solution: String by mutableStateOf("PROXY")
    private val guesses: MutableList<String> = mutableStateListOf("", "", "", "", "", "")

    val grid: List<List<LetterTileState>> by derivedStateOf {
        guesses.map { word ->
            when {
                word.length != 5 -> List(5) { i ->
                    when {
                        i < word.length -> Guess(word[i])
                        else -> Empty
                    }
                }
                else -> word.mapIndexed { index, character ->
                    when {
                        solution[index] == character -> Correct(character)
                        solution.contains(character) -> IncorrectPosition(character)
                        else -> Incorrect(character)
                    }
                }
            }
        }
    }

    init {
        viewModelScope.launch {
            delay(1000)
            guesses[0] = "A"
            delay(200)
            guesses[0] = "AD"
            delay(200)
            guesses[0] = "ADI"
            delay(200)
            guesses[0] = "ADIE"
            delay(200)
            guesses[0] = "ADIEU."
            delay(200)
            guesses[0] = "ADIEU"

            delay(1000)
            guesses[1] = "S"
            delay(200)
            guesses[1] = "ST"
            delay(200)
            guesses[1] = "STO"
            delay(200)
            guesses[1] = "STOR"
            delay(200)
            guesses[1] = "STORY."
            delay(200)
            guesses[1] = "STORY"
        }
    }
}
