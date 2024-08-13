interface TrafficLightState {
    void change(TrafficLight light);
}

// Concrete state for Red
class RedState implements TrafficLightState {
    public void change(TrafficLight light) {
        System.out.println("Red light - Stop");
        light.setState(new GreenState()); // Transition to Green after Red
    }
}

// Concrete state for Green
class GreenState implements TrafficLightState {
    public void change(TrafficLight light) {
        System.out.println("Green light - Go");
        light.setState(new YellowState()); // Transition to Yellow after Green
    }
}

// Concrete state for Yellow
class YellowState implements TrafficLightState {
    public void change(TrafficLight light) {
        System.out.println("Yellow light - Caution");
        light.setState(new RedState()); // Transition to Red after Yellow
    }
}

// Context Class
class TrafficLight {
    private TrafficLightState state;

    public TrafficLight(TrafficLightState state) {
        this.state = state;
    }

    public void setState(TrafficLightState state) {
        this.state = state;
    }

    public void change() {
        this.state.change(this);
    }
}

// Main class to test the traffic light system
public class TrafficLightstate {
    public static void main(String[] args) {
        TrafficLight light = new TrafficLight(new RedState());
        light.change(); // Output: Red light - Stop
        light.change(); // Output: Green light - Go
        light.change(); // Output: Yellow light - Caution
        light.change(); // Back to Red and so on...
    }
}
