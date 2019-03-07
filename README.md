# anagramFinder

There are three java applications in /bin directory for finding anagrams for given word.
Each implement slightly different approach.
Each can be run with cli command: `java {application} {path to dictionary} {word}`

### AnagramMapper.class
1. Maps given word into hashmap.
2. Streams through dictionary words and filters out words with same lenght as search word.
3. Substracts characters of possible anagrams from search word map to see if value sum will equal 0 (hence anagram).

### AnagramPermuter.class
1. Maps all permutations of word into hashmap.
2. Streams through dictionary words and filters out words with same lenght as search word.
3. Will check if permutations hashmap contains filtered word.

### AnagramSorter.class
1. Sorts the search word.
2. Streams through dictionary words and filters out words with same lenght as search word.
3. Will sort each filtered word and check if it equals search word (hence anagram).
