package partnerCodeInHerePlease;

import simonDavidM.ButtonInterfaceDavid;
import simonDavidM.MoveInterfaceDavid;

/**
 * Created by dav1d on 1/9/17.
 */
public class Move implements MoveInterfaceDavid{
    private ButtonInterfaceDavid b; // button
    public Move(ButtonInterfaceDavid b){
    	this.b = b;
    }
    
    @Override
    public ButtonInterfaceDavid getButton() {
        return b;
    }
}
