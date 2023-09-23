import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class QuizExam {
    public static void main(String[] args) throws IOException, ParseException {

    try {
        String filelocation = ("./src/main/resources/users.json");
        JSONObject jsonobject=new JSONObject();
        JSONParser parser = new JSONParser();
        JSONArray users = (JSONArray) parser.parse(new FileReader(filelocation));

        Scanner sc = new Scanner(System.in);
        System.out.println("System:> Enter your username");
        String user_name = sc.nextLine();
        System.out.println("System:> Enter your password");
        String pass_word = sc.nextLine();

        for (int i = 0; i < users.size(); i++) {

            jsonobject= (JSONObject) users.get(i);

            String username = jsonobject.get("username").toString();
            String password = jsonobject.get("password").toString();
            String role = jsonobject.get("role").toString();

            if (user_name.equals(username) && pass_word.equals(password)) {
                if (role.equals("admin")) {
                    System.out.println("System:> Welcome admin! Please create new questions in the question bank.");
                    questionbank();
                    break;
                } else if (role.equals("student")) {
                    System.out.println("Welcome salman to the quiz!We will throw you 10 questions.Each MCQ mark is 1 and no negative marking.");
                    System.out.println("Are you ready? Press 's' to start.");
                    char ch = sc.next().charAt(0);
                    if (ch == 's') {
                        quiz();
                    }
                } else {
                    System.out.println("Invalid input");
                }
            }
        }
    } catch (Exception e) {
        System.out.println("Invalid Input");
    }
    }

    public static void questionbank() throws IOException, ParseException {
        char ch = 's';
        String fileName = "./src/main/resources/quiz.json";
        do {
            JSONParser jsonParser = new JSONParser();
            Object obj = jsonParser.parse(new FileReader(fileName));
            JSONObject questionObj = new JSONObject();

            Scanner input = new Scanner(System.in);
            System.out.println("System:> Input your question");
            questionObj.put("question", input.nextLine());
            System.out.println("System: Input option 1:");
            questionObj.put("option 1", input.nextLine());
            System.out.println("System: Input option 2:");
            questionObj.put("option 2", input.nextLine());
            System.out.println("System: Input option 3:");
            questionObj.put("option 3", input.nextLine());
            System.out.println("System: Input option 4:");
            questionObj.put("option 4", input.nextLine());
            System.out.println("System: Input answerkey");
            questionObj.put("answerkey", input.nextInt());

            JSONArray jsonArray = (JSONArray) obj;
            jsonArray.add(questionObj);


            FileWriter file = new FileWriter(fileName);
            file.write(jsonArray.toJSONString());
            file.flush();
            file.close();
            System.out.println("Saved successfully!");
            System.out.println("Do you want to add more questions?(press s for start and q for quit)");
            ch = input.next().charAt(0);
        } while (ch != 'q');
    }

    public static void quiz() throws IOException, ParseException {
        char ch = 's';
        do {
            String fileLocation = ("./src/main/resources/quiz.json");
            int marks = 0;
            int total_question = 10;

            Scanner scanner = new Scanner(System.in);
            Random random = new Random();

            JSONParser parser = new JSONParser();
            JSONArray questionarray = (JSONArray) parser.parse(new FileReader(fileLocation));

            for (int i = 0; i < total_question; i++) {

                int ques = random.nextInt(questionarray.size());

                JSONObject questionobject = (JSONObject) questionarray.get(ques);

                String question = questionobject.get("question").toString();
                String Option_1 = questionobject.get("option 1").toString();
                String Option_2 = questionobject.get("option 2").toString();
                String Option_3 = questionobject.get("option 3").toString();
                String Option_4 = questionobject.get("option 4").toString();
                String AnswerKey = questionobject.get("answerkey").toString();

                System.out.println("[Question " + (i + 1) + "]" + question);
                System.out.println("1. " + Option_1);
                System.out.println("2. " + Option_2);
                System.out.println("3. " + Option_3);
                System.out.println("4. " + Option_4);
                System.out.println("Give your answer:");
                String answer = scanner.next();


                if (AnswerKey.equals(answer)) {
                    System.out.println("Correct.");
                    marks = marks + 1;
                } else {
                    System.out.println("Wrong.");
                }
            }
            if (marks >= 8) {
                System.out.println("Excellent! You have got " + marks + " out of 10");
            } else if (marks >= 5 && marks < 8) {
                System.out.println("Good. You have got " + marks + " out of 10");
            } else if (marks >= 2 && marks < 5) {
                System.out.println("Very poor! You have got " + marks + " out of 10");
            } else {
                System.out.println("Very sorry you are failed. You have got " + marks + " out of 10");
            }
            System.out.println("Would you like to start again? press s for start or q for quit");
            ch = scanner.next().charAt(0);
            if (ch == 'q') {
                break;
            }
        } while (ch != 'q');
    }
}
