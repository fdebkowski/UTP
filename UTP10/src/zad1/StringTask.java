package zad1;

public class StringTask implements Runnable {

    TaskState taskstate;
    String s, res = "";
    int count;
    Thread thread;

    public StringTask(String s, int count) {
        this.s = s;
        this.count = count;
        taskstate = TaskState.CREATED;
        thread = new Thread(this);
    }

    @Override
    public void run() {
        taskstate = TaskState.RUNNING;
        for (int i = 0; i < count; i++) {
            res += s;
        }
        taskstate = TaskState.READY;
    }

    public String getResult() {
        return res;
    }

    public TaskState getState() {
        return this.taskstate;
    }

    public void start() {
        thread.start();
    }

    public void abort() {
        thread.interrupt();
        taskstate = TaskState.ABORTED;
    }

    public boolean isDone() {
        return taskstate == TaskState.ABORTED || taskstate == TaskState.READY;
    }

    public enum TaskState {CREATED, RUNNING, ABORTED, READY}

}
