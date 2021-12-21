The theme is very basic game mechanics (text-based dungeon crawling game).

Dungeon map is defined in input file. Each line specifies room name and connections to other rooms:

```
a0 n:a3 
a1 e:a2 s:a3 
a2 s:a5 w:a1 
a3 n:a1 s:a0 w:a4 
a4 e:a3 s:b0 
a5 n:a2 e:a6 
a6 e:a7 s:a8 w:a5 
a7 s:a9 w:a6 
a8 n:a6 e:a9 s:b6 
a9 n:a7 w:a8 
b0 n:a4 w:b1 
b1 e:b0 s:b2 
b2 n:b1 e:b3
b3 e:b4 w:b2
b4 e:b5 w:b3
b5 e:b6 w:b4 
b6 n:a8 w:b5 
```

a0 to b6 are room names (they can be any 2-character names for simplicity)
n, s, e, w are doors leading to next rooms located north, south, east or west from current room.

Task:
Implement simple game framework that allows you to interactively navigate through the dungeon (room by room). Could be CLI, Web or whatever you find comfortable.

- [ ] Move by typing move direction (n, s, e or w).

- [ ] Display current situation, f.e.:

```
      a1-a2
      |  |
   a4-a3 a5-a6-a7
   |  |     |  |
b1-b0 **    a8-a9
|           |
b2-b3-b4-b5-b6
```

- [ ] Display avaialable moves

- [ ] Display shortest path between current room and desired room by typing room name


