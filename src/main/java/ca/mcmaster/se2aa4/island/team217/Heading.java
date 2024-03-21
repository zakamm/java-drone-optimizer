package ca.mcmaster.se2aa4.island.team217;

public enum Heading {
    N, // North
    E, // East
    S, // South
    W; // West

    // Method to get the left side direction
    public Heading leftSide() {
        switch (this) {
            case N:
                return W;
            case E:
                return N;
            case S:
                return E;
            case W:
                return S;
            default:
                throw new IllegalStateException("Unexpected value: " + this);
        }
    }

    // Method to get the right side direction
    public Heading rightSide() {
        switch (this) {
            case N:
                return E;
            case E:
                return S;
            case S:
                return W;
            case W:
                return N;
            default:
                throw new IllegalStateException("Unexpected value: " + this);
        }
    }

    // Method to get the opposite direction
    public Heading backSide() {
        switch (this) {
            case N:
                return S;
            case E:
                return W;
            case S:
                return N;
            case W:
                return E;
            default:
                throw new IllegalStateException("Unexpected value: " + this);
        }
    }
}

