// DOUBLE IT

const phrase = "hello";

// 1. Write a loop that "doubles" each character in a word.
// You'll need a new string variable to store the result.
// 2. Print the result.

console.log(
    // split the string into individual char array
    phrase.split('').map(function(v) {
      // iterate and update
      return v + v;
      // join the updated array
    }).join('')
  )

// Examples
// ===============
// "dog" -> "ddoogg"
// "what?" -> "wwhhaatt??"
// "" -> "" (empty string has nothing to double)
// " " -> "  " (but whitespace should be doubled)
// "open & shut" -> "ooppeenn  &&  sshhuutt"
// "Eep" -> "EEeepp"