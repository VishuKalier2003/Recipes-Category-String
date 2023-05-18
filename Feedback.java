/* You are given two string arrays positive_feedback and negative_feedback, containing the words denoting positive 
and negative feedback, respectively. Note that no word is both positive and negative. Initially every student has 0 
points. Each positive word in a feedback report increases the points of a student by 3, whereas each negative word 
decreases the points by 1. You are given n feedback reports, represented by a 0-indexed string array report and a 
0-indexed integer array student_id, where student_id[i] represents the ID of the student who has received the 
feedback report report[i]. The ID of each student is unique. Given an integer k, return the top k students after 
ranking them in non-increasing order by their points. In case more than one student has the same points, the one 
with the lower ID ranks higher.
* Eg 1 :    positive = ['smart', 'good', 'studious']         negative = ['not']
*           report = ['the student is studious', 'this one is smart']                 id = [1, 2]   k = 2
*           Output = [1, 2]
* Eg 2 :    positive = ['smart', 'brilliant', 'studious']    negative = ['not']
*           report = ['this student is not studious', 'the student is brilliant']     id = [1,2]    k = 2
*           Output = [2, 1]
*/
import java.util.*;
public class Feedback
{
      public List<Integer> RewardTopKStudents(String positive[], String negative[], String report[], int id[], int k)
      {
            List<Integer> answer = new ArrayList<Integer>();     //*  List -> O(k)
            Map<String, Integer> positiveMap = new HashMap<String, Integer>();     //*   HashMap -> O(t)
            Map<String, Integer> negativeMap = new HashMap<String, Integer>();     //*   HashMap -> O(t)
            for(int i = 0; i < positive.length; i++)      //! Storing -> O(t)
                  positiveMap.put(positive[i], 3);     // Score with word...
            for(int i = 0; i < negative.length; i++)      //! Storing -> O(t)
                  negativeMap.put(negative[i], -1);          // Score with word...
            int score = 0;
            PriorityQueue<int[]> MaxHeap = new PriorityQueue<int[]>((a, b) -> b[0] - a[0]);   //*  MaxHeap -> O(N)
            //? MaxHeap of Integer array, sorted on the basis of Score of student...
            for(int i = 0; i < report.length; i++)     //! Comparison -> O(N)
            {     // Checking report of every student...
                  String[] wrd = Words(report[i]);     //! Storing Words -> O(M)
                  for(int j = 0; j < wrd.length; j++)
                  {
                        if(positiveMap.containsKey(wrd[j]))    // Updating score from HashMaps...
                              score = score + positiveMap.get(wrd[j]);
                        else if(negativeMap.containsKey(wrd[j]))
                              score = score + negativeMap.get(wrd[j]);
                  }
                  int key[] = {score, id[i]};    // Key pair as Score and student id...
                  MaxHeap.add(key);    // Adding the Key pair to the MaxHeap...
                  score = 0;
            }
            while(k != 0)    //! Extracting -> O(k)
            {
                  answer.add(MaxHeap.peek()[1]);      // Getting the Top K students from MaxHeap...
                  MaxHeap.remove();
                  k--;
            }
            return answer;
      }
      public String[] Words(String string)    //! Word Splitting -> O(M)
      {
            String words[] = string.split(" ", -1); 
            return words;
      }
      public static void main(String args[])
      {
            Scanner sc = new Scanner(System.in);
            int x;
            System.out.print("Enter the number of positive feedback words : ");
            x = sc.nextInt();
            String posi[] = new String[x];
            for(int i = 0; i < posi.length; i++)
            {
                  System.out.print("Positive word : ");
                  posi[i] = sc.next();
            }
            System.out.print("Enter the number of negative feedback words : ");
            x = sc.nextInt();
            String nega[] = new String[x];
            for(int i = 0; i < nega.length; i++)
            {
                  System.out.print("Negative word : ");
                  nega[i] = sc.next();
            }
            System.out.print("Enter the number of students : ");
            x = sc.nextInt();
            sc.nextLine();       // Written to ensure the next input as sentence....
            String Repo[] = new String[x];
            for(int i = 0; i < Repo.length; i++)
            {
                  System.out.print("Enter report : ");
                  Repo[i] = sc.nextLine();
            }
            int Id[] = new int[x];
            for(int i = 0; i < x; i++)
            {
                  System.out.print("Enter student Id : ");
                  Id[i] = sc.nextInt();
            }
            System.out.print("Enter the value of K : ");
            x = sc.nextInt();
            Feedback feedback = new Feedback();      // Object creation...
            List<Integer> lst = new ArrayList<Integer>();
            System.out.println("The Top K Ranked students with their student id : ");
            lst = feedback.RewardTopKStudents(posi, nega, Repo, Id, x);    // Function calling...
            for(int i = 0; i < lst.size(); i++)
                  System.out.print(lst.get(i)+", ");
            sc.close();
      }
}



//! Time Complexity -> O(NM + t + k)
//* Space Complexity -> O(N + t + k)

/** //? DEDUCTIONS -
 * ? We put the positive and negative words in separate HashMaps, and split each sentence into words...
 * ? Then we use MaxHeap to put the array of score and id of the student, sorted on the basis of score...
 * ? Then we call the first k elements and extract their ids...
 */