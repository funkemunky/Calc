package cc.funkemunky.test;

public class Main {

    static float motionX = .2806f, motionZ, rotationYaw = 90, moveForward = 1, moveStrafing = 0;
    static boolean onGround = true;
    public static void main(String[] args) {
        jump();
        float strafe = 0, forward = 1, friction = 0.68f;
        float f = strafe * strafe + forward * forward;

        if (f >= 1.0E-4F) {
            f = (float) Math.sqrt(f);

            if (f < 1.0F) {
                f = 1.0F;
            }

            f = friction / f;
            strafe = strafe * f;
            forward = forward * f;
            float f1 = (float) Math.sin(90 * (float) Math.PI / 180.0F);
            float f2 = (float) Math.cos(90 * (float) Math.PI / 180.0F);
            Main.motionX += (double) (strafe * f2 - forward * f1);
            Main.motionZ += (double) (forward * f2 + strafe * f1);

            motionX*= friction;
            motionZ*= rotationYaw;

            System.out.println(motionX + ", " + motionZ + ", " + Math.sqrt(motionZ * motionZ + motionX * motionX));
        }
    }

    private static double getBaseMovementSpeed() {
        return 0.2806;
    }

    protected static void jump() {
        float f = rotationYaw * 0.017453292F;
        motionX -= (double) (Math.sin(f) * 0.2F);
        motionZ += (double) (Math.cos(f) * 0.2F);
    }

    public double getSpeed() {
        return Math.sqrt(motionX * motionX + motionZ * motionZ);
    }

    public static double getMovementSpeed() {
        return getBaseMovementSpeed() * 1;
    }

    public static void setSpeed(double d) {
        motionX =  (float) (-(Math.sin(getDirection()) * d));
        motionZ = (float) (Math.cos(getDirection()) * d);
    }

    public static float getDirection() {
        float direction = rotationYaw;
        if (moveForward < 0.0F) {
            direction += 180.0F;
        }

        float forward = 1.0F;
        if (moveForward < 0.0F) {
            forward = -0.5F;
        } else if (moveForward > 0.0F) {
            forward = 0.5F;
        }

        if (moveStrafing > 0.0F) {
            direction -= 90.0F * forward;
        } else if (moveStrafing < 0.0F) {
            direction += 90.0F * forward;
        }

        direction *= 0.017453292F;
        return direction;
    }

}
