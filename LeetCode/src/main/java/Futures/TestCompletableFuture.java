package src.main.java.Futures;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class TestCompletableFuture {

    public static void main(String[] args){

        MyTask task1 = new MyTask(5);
        MyTask task2 = new MyTask(11);
        MyTask task3 = new MyTask(3);
        MyTask task4 = new MyTask(5);
        List<MyTask> tasks = new ArrayList<>();
        tasks.add(task1);
        tasks.add(task2);
        tasks.add(task3);
        tasks.add(task4);
        TestCompletableFuture problem = new TestCompletableFuture();
        problem.useCompletableFuture(tasks);
    }

    public void useCompletableFuture(List<MyTask> tasks) {
        long start = System.nanoTime();
        List<CompletableFuture<Integer>> futures =
                tasks.stream()
                        .map(t -> CompletableFuture.supplyAsync(() -> t.calculate()))
                        .collect(Collectors.toList());

        List<Integer> result =
                futures.stream()
                        .map(CompletableFuture::join)
                        .collect(Collectors.toList());
        long duration = (System.nanoTime() - start) / 1_000_000;
        System.out.printf("Processed %d tasks in %d millis\n", tasks.size(), duration);
        System.out.println(result);
    }

}
