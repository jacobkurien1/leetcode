package main.java.Recursion;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/*
https://leetcode.com/problems/number-of-atoms/
Given a chemical formula (given as a string), return the count of each atom.

The atomic element always starts with an uppercase character, then zero or more lowercase letters, representing the name.

One or more digits representing that element's count may follow if the count is greater than 1.
If the count is 1, no digits will follow. For example, H2O and H2O2 are possible, but H1O2 is impossible.

Two formulas concatenated together to produce another formula. For example, H2O2He3Mg4 is also a formula.

A formula placed in parentheses, and a count (optionally added) is also a formula. For example, (H2O2) and (H2O2)3 are formulas.

Given a formula, return the count of all elements as a string in the following form: the first name (in sorted order),
followed by its count (if that count is more than 1), followed by the second name (in sorted order), followed by its count (if that count is more than 1), and so on.





Example 1:

Input: formula = "H2O"
Output: "H2O"
Explanation: The count of elements are {'H': 2, 'O': 1}.
Example 2:

Input: formula = "Mg(OH)2"
Output: "H2MgO2"
Explanation: The count of elements are {'H': 2, 'Mg': 1, 'O': 2}.
Example 3:

Input: formula = "K4(ON(SO3)2)2"
Output: "K4N2O14S4"
Explanation: The count of elements are {'K': 4, 'N': 2, 'O': 14, 'S': 4}.
Example 4:

Input: formula = "Be32"
Output: "Be32"


Constraints:

1 <= formula.length <= 1000
formula consists of English letters, digits, '(', and ')'.
formula is always valid.
 */
/*
Running time is O(n^2)
Space needed is O(n)
 */
public class AtomRepresentation {
    public String countOfAtoms(String formula) {
        TreeMap<String, Integer> ret = new TreeMap<>();
        Molecule mol = getMolecule(formula);
        for(Map.Entry<String, Integer> kvp : mol.atomFreq.entrySet()){
            ret.put(kvp.getKey(), kvp.getValue()*mol.freq);
        }
        StringBuilder sb = new StringBuilder();
        for(Map.Entry<String, Integer> kvp: ret.entrySet()){
            sb.append(kvp.getKey() + (kvp.getValue()==1?"":Integer.toString(kvp.getValue())));
        }
        return sb.toString();
    }

    int fIndex = 0;

    Molecule getMolecule(String formula){
        if(fIndex>=formula.length()){
            return null;
        }
        Molecule currMolecule = new Molecule();
        while (fIndex<formula.length()){
            char c= formula.charAt(fIndex);
            if(c == ')'){
                fIndex++;
                int moleculeFreq = getMoleculeFreq(formula);
                currMolecule.freq = moleculeFreq;
                return currMolecule;
            } else if (c >= 'A' && c<='Z'){
                StringBuilder sb = new StringBuilder();
                sb.append(Character.toString(formula.charAt(fIndex++)));
                while(fIndex<formula.length() && formula.charAt(fIndex) >='a' && formula.charAt(fIndex) <='z'){
                    sb.append(Character.toString(formula.charAt(fIndex++)));
                }
                int freq = currMolecule.atomFreq.getOrDefault(sb.toString(), 0);
                currMolecule.atomFreq.put(sb.toString(), freq + getMoleculeFreq(formula));
            } else if (c == '('){
                fIndex++;
                Molecule getMol= getMolecule(formula);
                for(Map.Entry<String, Integer> kvp : getMol.atomFreq.entrySet()){
                    int freq = currMolecule.atomFreq.getOrDefault(kvp.getKey(), 0);
                    freq += getMol.freq*kvp.getValue();
                    currMolecule.atomFreq.put(kvp.getKey(), freq);
                }
            }
        }
        return currMolecule;
    }

    int getMoleculeFreq(String formula){
        int moleculeFreq = 0;
        while(fIndex<formula.length() && formula.charAt(fIndex) >='0' && formula.charAt(fIndex) <='9') {
            moleculeFreq *= 10;
            moleculeFreq += formula.charAt(fIndex) - '0';
            fIndex++;
        }
        return moleculeFreq == 0?1:moleculeFreq;
    }

    class Molecule {
        Map<String, Integer> atomFreq = new HashMap<>();
        int freq = 1;
    }
}
