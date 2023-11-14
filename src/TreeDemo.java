import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

class Pair{
    State state;
    DefaultMutableTreeNode Node;

    public Pair(State state, DefaultMutableTreeNode node) {
        this.state = state;
        Node = node;
    }
}
public class TreeDemo extends JFrame {
    public TreeDemo(HashMap<State, ArrayList<State>> treeMap, HashMap<Long, Integer> explored, Hasher hasher, State state) {
        // Create the root node and child nodes for the tree
        HashMap<DefaultMutableTreeNode,State> nodeMap = new HashMap<>();
        DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode(explored.get(hasher.hashGrid(state)));
        Stack<Pair> stack = new Stack<>();
        stack.push(new Pair(state,rootNode));
        nodeMap.put(rootNode,state);
        while (!stack.isEmpty()){
            Pair current = stack.pop();
            if(treeMap.get(current.state) ==null)
                continue;
            for(State s : treeMap.get(current.state)){
                DefaultMutableTreeNode node = new DefaultMutableTreeNode(explored.get(hasher.hashGrid(s)));
                current.Node.add(node);
                nodeMap.put(node,s);
                stack.push(new Pair(s,node));
            }
        }
        // Create the tree with the root node
        JTree tree = new JTree(rootNode);

        tree.addTreeSelectionListener(new TreeSelectionListener() {

            @Override
            public void valueChanged(TreeSelectionEvent e) {
                // Get the selected node
                DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();

                if (selectedNode != null) {
                    // Process the selected node (e.g., display information)
                    JOptionPane.showMessageDialog(
                            null,
                                  nodeMap.get(selectedNode).printGridTree()
                            );
                }
            }
        });


        // Create a scroll pane to hold the tree
        JScrollPane scrollPane = new JScrollPane(tree);

        // Add the scroll pane to the frame
        add(scrollPane);

        // Set frame properties
        setTitle("Tree Demo");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null); // Center the frame
    }

}