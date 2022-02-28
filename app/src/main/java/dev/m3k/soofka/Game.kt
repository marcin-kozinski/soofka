package dev.m3k.soofka

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

object GameViewModel2 {
    // val solution
    // val guesses: StateFlow<String>
    
    // val grid: StateFlow<List<List<LetterTileState>>>
}

@Composable
fun GameGrid(words: List<List<LetterTileState>>) {
    val sth = remember { mutableStateOf("sth") }
    Column(Modifier.padding(2.dp)) {
        for (word in words) {
            WordRow(word, Modifier.padding(vertical = 2.dp))
        }
    }
}

@Preview
@Composable
fun GameGridPreview() {
    GameGrid(
        listOf(
            listOf(
                Correct('W'),
                Guess('E'),
                Guess('A'),
                Guess('R'),
                Guess('Y'),
            ),
            listOf(
                Guess('P'),
                IncorrectPosition('I'),
                Guess('L'),
                Guess('L'),
                Guess('S'),
            ),
            listOf(
                Guess('V'),
                Guess('A'),
                Guess('G'),
                Incorrect('U'),
                Guess('E'),
            ),
            listOf(
                Empty,
                Empty,
                Empty,
                Empty,
                Empty,
            ),
        )
    )
}

@Composable
fun WordRow(
    letters: List<LetterTileState>,
    modifier: Modifier = Modifier,
) {
    Row(modifier) {
        for (letter in letters) {
            LetterTile(letter, Modifier.padding(horizontal = 2.dp))
        }
    }
}

@Preview
@Composable
fun WordRowPreview() {
    WordRow(listOf(
        Incorrect('Q'),
        IncorrectPosition('E'),
        Correct('D'),
        Guess('X'),
        Empty,
    ))
}

sealed class LetterTileState
object Empty : LetterTileState()
data class Guess(val letter: Char): LetterTileState()
data class Incorrect(val letter: Char) : LetterTileState()
data class IncorrectPosition(val letter: Char) : LetterTileState()
data class Correct(val letter: Char) : LetterTileState()

@Composable
fun LetterTile(
    state: LetterTileState,
    modifier: Modifier = Modifier,
) {
    when (state) {
        Empty -> LetterTile(
            letter = null,
            letterColor = Color.Unspecified,
            backgroundColor = Color.Transparent,
            borderColor = Color.LightGray,
            modifier = modifier,
        )
        is Guess -> LetterTile(
            letter = state.letter,
            letterColor = Color.DarkGray,
            backgroundColor = Color.Transparent,
            borderColor = Color.LightGray,
            modifier = modifier,
        )
        is Incorrect -> LetterTile(
            letter = state.letter,
            letterColor = Color.White,
            backgroundColor = Color.Gray,
            modifier = modifier,
        )
        is IncorrectPosition -> LetterTile(
            letter = state.letter,
            letterColor = Color.White,
            backgroundColor = Color(0xFFC9B458),
            modifier = modifier,
        )
        is Correct -> LetterTile(
            letter = state.letter,
            letterColor = Color.White,
            backgroundColor = Color(0xFF6AAA64),
            modifier = modifier,
        )
    }
}

@Preview
@Composable
fun EmptyLetterTilePreview() {
    LetterTile(Empty)
}

@Preview
@Composable
fun GuessLetterTilePreview() {
    LetterTile(Guess('X'))
}

@Preview
@Composable
fun IncorrectLetterTilePreview() {
    LetterTile(Incorrect('Q'))
}

@Preview
@Composable
fun IncorrectPositionLetterTilePreview() {
    LetterTile(IncorrectPosition('E'))
}

@Preview
@Composable
fun CorrectLetterTilePreview() {
    LetterTile(Correct('D'))
}

@Composable
fun LetterTile(
    letter: Char?,
    letterColor: Color,
    backgroundColor: Color,
    modifier: Modifier = Modifier,
    borderColor: Color = backgroundColor,
) {
    Box(
        modifier
            .size(48.dp)
            .background(backgroundColor)
            .border(1.dp, borderColor),
        contentAlignment = Alignment.Center
    ) {
        if (letter != null) {
            Text(
                letter.toString(),
                color = letterColor,
                style = MaterialTheme.typography.headlineSmall
            )
        }
    }
}

@Preview
@Composable
fun LetterTilePreview() {
    LetterTile(
        letter = 'ü',
        letterColor = Color.Yellow,
        backgroundColor = Color.Green,
        borderColor = Color.Magenta,
    )
}
