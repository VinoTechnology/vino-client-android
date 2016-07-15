package com.example.vino.nodeobject;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ObjectGroup {
    static int i = 0;
    //static boolean isfirst=true

    public ObjectGroup(InputStream is) {
        group = new HashMap<String, NodeObject>();
        seq = new ArrayList<String>();
        state = new ArrayList<Integer>(seq.size());
        readConfig(is);
    }

    public void readConfig(InputStream is) {
        BufferedReader reader = null;
        try {
            System.out.println("����Ϊ��λ��ȡ�ļ����ݣ�һ�ζ�һ���У�");
            reader = new BufferedReader(new InputStreamReader(is));
            String tempString = null;
            String s = null;
            int line = 1;
            // һ�ζ���һ�У�ֱ������nullΪ�ļ�����
            while ((tempString = reader.readLine()) != null) {
                // ��ʾ�к�
                System.out.println("line " + line + ": " + tempString);
                Log.i("VINO_READCONFIG", tempString);
                s = parse(s, tempString);
                line++;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }

        //no implement
    }

    public NodeObject getNodeObject(String name) {

        return null;
    }

    public void nextStep() {
        if (i >= 0 && i < seq.size() - 1) {
            if (state.get(i) == 0) {
                Transfer _adapter = Transfer.getInstance();
                NodeObject ob = group.get(seq.get(i));
                _adapter.sendOnePacket(MessageType.NODE_NAME, ob.name);
                _adapter.sendOnePacket(MessageType.NODE_TRANSLATION, ob.translation.x, ob.translation.y, ob.translation.z);
                state.set(i, 1);
            }
        }

        if (i < seq.size() - 1)
            i++;

        if (i >= seq.size() - 1)
            i = seq.size() - 1;

    }

    public void prevStep() {
        if (i > 0)
            i--;

        if (i >= 0 && i < seq.size()) {
            if (state.get(i) == 1) {
                Transfer _adapter = Transfer.getInstance();
                NodeObject ob = group.get(seq.get(i));
                _adapter.sendOnePacket(MessageType.NODE_NAME, ob.name);
                _adapter.sendOnePacket(MessageType.NODE_TRANSLATION, -ob.translation.x, -ob.translation.y, -ob.translation.z);
                state.set(i, 0);
            }
        }

        if (i < 0)
            i = 0;
        //if(i>0)
        //i--;
    }

    public void addNodeObject() {
        //no implememt
    }

    public int getNodeObjectSize() {
        return group.size();
    }

    private String parse(String name, String line) {
        line.trim();
        if (line.charAt(0) == '#') {
            return null;
        } else if (!line.matches(".*translation.*") && !line.matches(".*rotation.*")) {
            Log.i("~~~~~~~~~~~VINO_READCONFIG NAME~~~~~~~~~", line);
            NodeObject no = new NodeObject();
            no.name = line;
            group.put(no.name, no);
            seq.add(line);
            state.add(0);
            return line;
        } else if (line.matches(".*translation.*")) {
            Scanner scan = new Scanner(line);

            if (name != null) {
                Log.i("~~~~~~~~~~~VINO_READCONFIG~~~~~~~~~", line);
                Log.i("~~~~~~~~~~~VINO_READCONFIG~~~~~~~~~", name);
                NodeObject no = group.get(name);
                //NodeObject no=new NodeObject();
                String t = scan.next();
                no.translation.x = scan.nextFloat();
                no.translation.y = scan.nextFloat();
                no.translation.z = scan.nextFloat();
                group.put(name, no);
            }

        } else if (line.matches(".*rotation.*")) {
            //no implememt
        }

        return null;
    }

    private Map<String, NodeObject> group;
    private ArrayList<String> seq;
    private ArrayList<Integer> state;
}