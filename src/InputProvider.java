public interface InputProvider {
    public default Position getAction(Position current_Pos, char input)
    {
        switch(input) {
            case 'w':
                current_Pos.Move(current_Pos.getX(), current_Pos.getY()+1);
                break;
            case 'a':
                current_Pos.Move(current_Pos.getX()-1, current_Pos.getY());
                break;
            case 's':
                current_Pos.Move(current_Pos.getX(), current_Pos.getY()-1);
                break;
            case 'd':
                current_Pos.Move(current_Pos.getX()+1, current_Pos.getY());
                break;
            case 'e':
                current_Pos.Move(-1,-1);
                break;
            default:
                return current_Pos;

        }
        return current_Pos;
    }

}
