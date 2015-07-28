        import java.util.BitSet;

/* XML Excerpt

<command>
	<robot>A4</robot>
	<offon>On</offon>
	<speed>Low</speed>
	<horizontal>CounterClockwise</horizontal>
	<vertical>Up</vertical>
	<time>190</time>
</command>

*/
public class Robot
{
    public static int OFF=0, ON=1, INVALID=0, OK=1, LOW=1, HIGH=2, CLOCKWISE=1, COUNTERCLOCKWISE=2, UP=1, DOWN=2, MINTIME=1, MAXTIME=255;

    public class Command
    {
        Command() { Reset(); }
        void setOffOn(int value) { offon = value; }
        void setError(int value) { errorcode = value; }
        void setSpeed(int value) { speed = value; }
        void setHorizontal(int value) { horizontal = value; }
        void setVertical(int value) { vertical = value; }
        void setTime(int value) { time = value; }
        boolean Execute() throws InterruptedException
        {
            boolean bvalid = Validate();
            if (bvalid == true)
            {
                Output();
                Thread.sleep(time);
            }
            Reset();
            return bvalid;
        }
        void Output()
        {
            System.out.print("Command = On: " + offon + '\t');
            System.out.print("Speed: " + speed + '\t');
            System.out.print("Horizontal: " + horizontal + '\t');
            System.out.print("Vertical: " + vertical + '\t');
            System.out.print("Time: " + time + '\n');
        }
        boolean testValue(int value)
        {
            if (value != 0)
                return true;
            return false;
        }
        boolean Validate()
        {
            boolean bvalid = false;
            if ((offon & ON) > 0)
            {
                bvalid = true;
                if (!testValue(speed))
                    bvalid = false;
                if (!testValue(horizontal))
                    bvalid = false;
                if (!testValue(vertical))
                    bvalid = false;
                if (!testValue(time))
                    bvalid = false;
            }
            if (!bvalid)
                errorcode = INVALID;
            return errorcode != INVALID;
        }
        void Reset()
        {
            offon = OFF;
            errorcode = OK;
            speed = 0;
            horizontal = 0;
            vertical = 0;
            time = 0;
        }
        // 0 = off 1 = on
        int offon;
        // 0 = invalid 1 = ok
        int errorcode;
        // 1 = low 2 = high
        int speed;
        // 1 = clockwise 2 = counter-clockwise
        int horizontal;
        // 1 = up 2 = down
        int vertical;
        // time (maximum 255 ms)
        int time;
    };

    public class iRobot
    {
        iRobot(){ }
        boolean Execute(Command c) throws InterruptedException
        {
            boolean bvalue = c.Execute();
            return bvalue;
        }
    };

    void RobotTest() throws InterruptedException
    {
        Command command = new Command();
        iRobot robot = new iRobot();

        // invalid
        if (robot.Execute(command) == true)
            System.out.println("OK\n");
        else
            System.out.println("EROR\n");

        // valid
        command.setOffOn(ON);
        command.setSpeed(HIGH);
        command.setHorizontal(COUNTERCLOCKWISE);
        command.setVertical(UP);
        command.setTime(128);
        if (robot.Execute(command) == true)
            System.out.println("OK\n");
        else
            System.out.println("EROR\n");
    }

    public static void main(String[] args) throws InterruptedException
    {
        Robot robot = new Robot();
        robot.RobotTest();
    }
}



