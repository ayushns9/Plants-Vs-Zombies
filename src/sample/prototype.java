package sample;

public class prototype {

    private static prototype pt = null;

    public static prototype getInstance(){
        if(pt == null){
            pt = new prototype();
        }
        return pt;
    }

    public static Plants getclone(Plants o) throws CloneNotSupportedException {
        return (Plants) o.clone();
    }

    public static Zombies getclone(Zombies o) throws CloneNotSupportedException {
        return (Zombies) o.clone();
    }

    public static Pea getclone(Pea o) throws CloneNotSupportedException {
        return (Pea) o.clone();
    }

    public static LawnMover getclone(LawnMover o) throws  CloneNotSupportedException {
        return  (LawnMover) o.clone();
    }

}
