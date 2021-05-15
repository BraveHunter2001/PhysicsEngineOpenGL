package engine;

import org.joml.Matrix4f;
import org.joml.Vector2f;
import org.joml.Vector3f;

public class Camera {

    private Matrix4f projectionMatrix, viewMatrix;
    public Vector2f position;

    public Camera(Vector2f position){
        this.position = position;
        this.projectionMatrix = new Matrix4f();
        this.viewMatrix = new Matrix4f();
        adjustProjection();
    }

    public void adjustProjection(){
        projectionMatrix.identity();
        int widthWindow = Window.get().width;
        int heightWindow = Window.get().height;
        //32*32 pixel by 9 * 2 grid RIGHT
        //32*32 pixel by 6 * 2 grid UP
        projectionMatrix.ortho(0.0f, (float)widthWindow, 0.0f, (float)heightWindow, 0.0f, 100.0f);
    }

    public Matrix4f getViewMatrix() {
        Vector3f cameraFront = new Vector3f(0.0f, 0.0f, -1.0f);
        Vector3f cameraUp = new Vector3f(0.0f, 1.0f, 0.0f);

        this.viewMatrix.identity();
        this.viewMatrix = viewMatrix.lookAt(new Vector3f(position.x, position.y, 20.0f),
                                            cameraFront.add(position.x, position.y, 0.0f),
                                            cameraUp);

        return this.viewMatrix;
    }


    public Matrix4f getProjectionMatrix() {

        return this.projectionMatrix;
    }
}
