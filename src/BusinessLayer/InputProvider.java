package BusinessLayer;

public class InputProvider {
    public InputProvider() { }

    public Position getNewPosition(Position current_Pos, char input)
        { //**The function should not be "move" but "try"/"interact" because f.e if the new pos is a wall or anything we can't just change pos***
            ///****we suppose you need to add q and not just default for any key (so won't confused)***
            return switch (input) {
                case 'w' -> new Position(current_Pos.getX(), current_Pos.getY() - 1);
                case 'a' -> new Position(current_Pos.getX() - 1, current_Pos.getY());
                case 's' -> new Position(current_Pos.getX(), current_Pos.getY() + 1);
                case 'd' -> new Position(current_Pos.getX() + 1, current_Pos.getY());
                case 'e' -> new Position(-1, -1);
                case 'q' -> current_Pos;
                default -> null;
            };
        }
    }


