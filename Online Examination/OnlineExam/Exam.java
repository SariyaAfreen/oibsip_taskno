package OnlineExam;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class Exam {
    private ArrayList<Question> questions;
    private int score;
    private Timer timer;

    public Exam() {
        questions = new ArrayList<>();
        score = 0;
        loadQuestions();
    }

    private void loadQuestions() {
        questions.add(new Question("What is the capital of France?", new String[]{"Berlin", "Paris", "Madrid", "Rome"}, 2));
        questions.add(new Question("What is 2 + 2?", new String[]{"3", "4", "5", "6"}, 2));
        questions.add(new Question("Which planet is known as the Red Planet?", new String[]{"Earth", "Venus", "Mars", "Jupiter"}, 3));
    }

    public void startExam(Scanner scanner) {
        System.out.println("Exam started! You have 1 minute to complete.");

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("Time's up! Auto-submitting...");
                submitExam();
            }
        }, 60000); // 1 minute in milliseconds

        for (Question question : questions) {
            question.displayQuestion();
            System.out.print("Your answer: ");
            int answer = scanner.nextInt();
            if (question.checkAnswer(answer)) {
                score++;
            }
        }

        submitExam();
    }

    private void submitExam() {
        timer.cancel();
        System.out.println("Exam submitted. Your score: " + score + "/" + questions.size());
    }
}
