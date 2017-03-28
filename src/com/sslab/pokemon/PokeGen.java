package com.sslab.pokemon;

import com.google.gson.Gson;
import com.google.gson.stream.JsonWriter;
import com.sslab.pokemon.data.PokemonIndividualData;
import com.sslab.pokemon.data.PokemonSpeciesData;
import com.sslab.pokemon.sprite.PokemonSprite;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by jerry on 2017/3/19.
 */
public class PokeGen {
    private JComboBox speciesComboBox;
    private JPanel root;

    private JButton deleteButton;
    private JButton saveButton;

    private JPanel slot0;
    private JPanel slot1;
    private JPanel slot2;
    private JPanel slot3;
    private JPanel slot4;
    private JPanel slot5;

    private JTextField nickNameField;
    private JTextField hpField;
    private JTextField atkField;
    private JTextField defField;
    private JTextField spAtkField;
    private JTextField spDefField;
    private JTextField speedField;

    private JButton calculateButton;
    private JTextField effectHP;
    private JTextField effectATT;
    private JTextField effectDEF;
    private JTextField effectSAT;
    private JTextField effectSDE;
    private JTextField effectSPE;
    private JTextField LV;
    private JLabel stat_HP;
    private JLabel stat_Att;
    private JLabel stat_Def;
    private JLabel stat_Sat;
    private JLabel stat_Sde;
    private JLabel stat_Spe;

    private JPanel currentSelectedPanel;

    private ArrayList<JTextField> statFields;


    Pokedex pokedex;
    HashMap<JPanel, PokemonIndividualData> pokemonMap;

    public PokeGen() {

        pokedex = new Pokedex("bin/pokemonData.json");
        pokemonMap = new HashMap<>();
        int sizeOfPokedex = pokedex.getPokemonSize();
        for (int c1 = 0; c1 < sizeOfPokedex; c1++) {
            PokemonSpeciesData now = pokedex.getPokemonData(c1);
            speciesComboBox.addItem(now.getId() + " : " + now.getSpeciesName());
        }
        //speciesComboBox.setSelectedIndex(0);
        slot0.setBorder(BorderFactory.createBevelBorder(1));
        currentSelectedPanel = slot0;

        // speciesComboBox.actionPerformed(new ActionEvent(speciesComboBox,0,""));

        speciesComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //get the index
                int index = speciesComboBox.getSelectedIndex();
                //check is this panel has data
                if (!pokemonMap.containsKey(currentSelectedPanel))
                {
                    //initial the textfield
                    PokemonSpeciesData now = pokedex.getPokemonData(index);
                    nickNameField.setText(now.getSpeciesName());
                    hpField.setText(Integer.toString(0));
                    atkField.setText(Integer.toString(0));
                    defField.setText(Integer.toString(0));
                    spAtkField.setText(Integer.toString(0));
                    spDefField.setText(Integer.toString(0));
                    speedField.setText(Integer.toString(0));
                    //put the image
                    JLabel nowLabel = (JLabel) currentSelectedPanel.getComponent(0);
                    nowLabel.setIcon(new ImageIcon(PokemonSprite.getSprite(index)));


                    int [] val = {
                            Integer.valueOf(hpField.getText()),
                            Integer.valueOf(atkField.getText()),
                            Integer.valueOf(defField.getText()),
                            Integer.valueOf(spAtkField.getText()),
                            Integer.valueOf(spDefField.getText()),
                            Integer.valueOf(speedField.getText()),
                    };
                    //create a new invidividualdata
                    PokemonIndividualData nowIndividualData= new PokemonIndividualData(nickNameField.getText(),index,val);
                    //put in the map
                    pokemonMap.put(currentSelectedPanel,nowIndividualData);


                }
                else//already have data
                {
                    //change the icon
                    PokemonIndividualData nowIndividualData=pokemonMap.get(currentSelectedPanel);
                    nowIndividualData.ID = index;
                    JLabel nowLabel = (JLabel) currentSelectedPanel.getComponent(0);
                    nowLabel.setIcon(new ImageIcon(PokemonSprite.getSprite(index)));
                    pokemonMap.put(currentSelectedPanel,nowIndividualData);
                }
            }
        });
        slot0.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                //check this panel has data
                if(pokemonMap.containsKey(currentSelectedPanel))
                {
                    //check the value is between is 0~31
                    if (!checkValueRight())
                    {
                        //wrong message
                        JOptionPane.showMessageDialog(null, "Please check all your value is between 0 and31");
                        return;
                    }
                    else//save the data
                        setPokemon(currentSelectedPanel);
                }

                //change the border
                slot0.setBorder(BorderFactory.createBevelBorder(1));
                slot1.setBorder(BorderFactory.createBevelBorder(0));
                slot2.setBorder(BorderFactory.createBevelBorder(0));
                slot3.setBorder(BorderFactory.createBevelBorder(0));
                slot4.setBorder(BorderFactory.createBevelBorder(0));
                slot5.setBorder(BorderFactory.createBevelBorder(0));


                //change the select panel
                currentSelectedPanel = slot0;
                loadPokemon(currentSelectedPanel);
            }
        });
        slot1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if(pokemonMap.containsKey(currentSelectedPanel))
                {
                    if (!checkValueRight())
                    {
                        JOptionPane.showMessageDialog(null, "Please check all your value is between 0 and31");
                        return;
                    }
                    else
                        setPokemon(currentSelectedPanel);
                }
                slot0.setBorder(BorderFactory.createBevelBorder(0));
                slot1.setBorder(BorderFactory.createBevelBorder(1));
                slot2.setBorder(BorderFactory.createBevelBorder(0));
                slot3.setBorder(BorderFactory.createBevelBorder(0));
                slot4.setBorder(BorderFactory.createBevelBorder(0));
                slot5.setBorder(BorderFactory.createBevelBorder(0));

                currentSelectedPanel = slot1;
                loadPokemon(currentSelectedPanel);


            }
        });
        slot2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if(pokemonMap.containsKey(currentSelectedPanel))
                {
                    if (!checkValueRight())
                    {
                        JOptionPane.showMessageDialog(null, "Please check all your value is between 0 and31");
                        return;
                    }
                    else
                        setPokemon(currentSelectedPanel);
                }
                slot0.setBorder(BorderFactory.createBevelBorder(0));
                slot1.setBorder(BorderFactory.createBevelBorder(0));
                slot2.setBorder(BorderFactory.createBevelBorder(1));
                slot3.setBorder(BorderFactory.createBevelBorder(0));
                slot4.setBorder(BorderFactory.createBevelBorder(0));
                slot5.setBorder(BorderFactory.createBevelBorder(0));

                currentSelectedPanel = slot2;
                loadPokemon(currentSelectedPanel);
            }
        });
        slot3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if(pokemonMap.containsKey(currentSelectedPanel))
                {
                    if (!checkValueRight())
                    {
                        JOptionPane.showMessageDialog(null, "Please check all your value is between 0 and31");
                        return;
                    }
                    else
                        setPokemon(currentSelectedPanel);
                }
                slot0.setBorder(BorderFactory.createBevelBorder(0));
                slot1.setBorder(BorderFactory.createBevelBorder(0));
                slot2.setBorder(BorderFactory.createBevelBorder(0));
                slot3.setBorder(BorderFactory.createBevelBorder(1));
                slot4.setBorder(BorderFactory.createBevelBorder(0));
                slot5.setBorder(BorderFactory.createBevelBorder(0));

                currentSelectedPanel = slot3;
                loadPokemon(currentSelectedPanel);
            }
        });
        slot4.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if(pokemonMap.containsKey(currentSelectedPanel))
                {
                    if (!checkValueRight())
                    {
                        JOptionPane.showMessageDialog(null, "Please check all your value is between 0 and31");
                        return;
                    }
                    else
                        setPokemon(currentSelectedPanel);
                }
                slot0.setBorder(BorderFactory.createBevelBorder(0));
                slot1.setBorder(BorderFactory.createBevelBorder(0));
                slot2.setBorder(BorderFactory.createBevelBorder(0));
                slot3.setBorder(BorderFactory.createBevelBorder(0));
                slot4.setBorder(BorderFactory.createBevelBorder(1));
                slot5.setBorder(BorderFactory.createBevelBorder(0));

                currentSelectedPanel = slot4;
                loadPokemon(currentSelectedPanel);
            }
        });
        slot5.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if(pokemonMap.containsKey(currentSelectedPanel))
                {
                    if (!checkValueRight())
                    {
                        JOptionPane.showMessageDialog(null, "Please check all your value is between 0 and31");
                        return;
                    }
                    else
                        setPokemon(currentSelectedPanel);
                }
                slot0.setBorder(BorderFactory.createBevelBorder(0));
                slot1.setBorder(BorderFactory.createBevelBorder(0));
                slot2.setBorder(BorderFactory.createBevelBorder(0));
                slot3.setBorder(BorderFactory.createBevelBorder(0));
                slot4.setBorder(BorderFactory.createBevelBorder(0));
                slot5.setBorder(BorderFactory.createBevelBorder(1));

                currentSelectedPanel = slot5;
                loadPokemon(currentSelectedPanel);
            }
        });

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                //check this panel has data
                if(pokemonMap.containsKey(currentSelectedPanel))
                {
                    if (!checkValueRight())
                    {
                        //check the value
                        JOptionPane.showMessageDialog(null, "Please check all your value is between 0 and31");
                        return;
                    }
                    else
                    {
                        setPokemon(currentSelectedPanel);
                    }
                }
                //save the data
                try {
                    savedata("bin/morris_new_pokemon.json");
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //check the panel has data
                if(pokemonMap.containsKey(currentSelectedPanel))
                {
                    //warning
                    int n = JOptionPane.showConfirmDialog(
                            null,
                            "Do you sure you want to delete this pokemon?",
                            "Delete Warning",
                            JOptionPane.YES_NO_OPTION);
                    //if really want to delete
                    if(n == JOptionPane.YES_OPTION)
                    {
                        //clear all datafield
                        pokemonMap.remove(currentSelectedPanel);
                        nickNameField.setText("");
                        hpField.setText("");
                        atkField.setText("");
                        defField.setText("");
                        spAtkField.setText("");
                        spDefField.setText("");
                        speedField.setText("");
                        JLabel nowLabel = (JLabel) currentSelectedPanel.getComponent(0);
                        nowLabel.setIcon(null);

                    }
                    else// if no, do nothing
                        return;
                }
                else
                {
                    return;
                }
            }
        });

        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //if nothing input,set to 0
                if(effectHP.getText().equals(""))
                    effectHP.setText("0");
                if(effectATT.getText().equals(""))
                    effectATT.setText("0");
                if(effectDEF.getText().equals(""))
                    effectDEF.setText("0");
                if(effectSAT.getText().equals(""))
                    effectSAT.setText("0");
                if(effectSDE.getText().equals(""))
                    effectSDE.setText("0");
                if(effectSPE.getText().equals(""))
                    effectSPE.setText("0");



                if(pokemonMap.containsKey(currentSelectedPanel))
                {
                    if (!checkValueRight())
                    {
                        JOptionPane.showMessageDialog(null, "Please check all your value is between 0 and31");
                        return;
                    }
                    else
                        setPokemon(currentSelectedPanel);
                }
                //store the data in array
                int [] val = {
                        Integer.valueOf(effectHP.getText()),
                        Integer.valueOf(effectATT.getText()),
                        Integer.valueOf(effectDEF.getText()),
                        Integer.valueOf(effectSAT.getText()),
                        Integer.valueOf(effectSDE.getText()),
                        Integer.valueOf(effectSPE.getText()),
                };
                //check the value in effect value is correct
                int total= 0;
                boolean flag = true;
                for(int c1=0;c1<6;c1++)
                {
                    total+=val[c1];
                    if(val[c1]>255||val[c1]<0)
                    {
                        JOptionPane.showMessageDialog(null, "Please check all your Effect value  is between 0 and255");
                        return;
                    }
                }

                if(total>510)
                {
                    JOptionPane.showMessageDialog(null, "Please check  your Effect value's total  is between 0 and 510");
                }
                else
                {
                    //show the stat of pokemon
                    int[] IV = pokemonMap.get(currentSelectedPanel).getPokemonValueDate();
                    int[] SS = pokedex.getPokemonData(pokemonMap.get(currentSelectedPanel).ID).getSpeciesValue().getValArray();
                    int lv = Integer.valueOf(LV.getText());
                    stat_HP.setText(Integer.toString((SS[0]*2+IV[0]+(val[0]/4))*(lv/100)+lv+10));
                    stat_Att.setText(Integer.toString(((SS[1]*2+IV[1]+(val[1]/4))*(lv/100)+5)));
                    stat_Def.setText(Integer.toString(((SS[2]*2+IV[2]+(val[2]/4))*(lv/100)+5)));
                    stat_Sat.setText(Integer.toString(((SS[3]*2+IV[3]+(val[3]/4))*(lv/100)+5)));
                    stat_Sde.setText(Integer.toString(((SS[4]*2+IV[4]+(val[4]/4))*(lv/100)+5)));
                    stat_Spe.setText(Integer.toString(((SS[5]*2+IV[5]+(val[5]/4))*(lv/100)+5)));

                }


            }
        });
    }

    public void setPokemon(JPanel panel) {
        //get the data
        PokemonIndividualData now=pokemonMap.get(currentSelectedPanel);
        //change data
        now.nickName = nickNameField.getText();
        now.pokemonValueDate[0]=(Integer.valueOf(hpField.getText()));
        now.pokemonValueDate[1]=(Integer.valueOf(atkField.getText()));
        now.pokemonValueDate[2]=(Integer.valueOf(defField.getText()));
        now.pokemonValueDate[3]=(Integer.valueOf(spAtkField.getText()));
        now.pokemonValueDate[4]=(Integer.valueOf(spDefField.getText()));
        now.pokemonValueDate[5]=(Integer.valueOf(speedField.getText()));
        //restore the data
        pokemonMap.put(currentSelectedPanel,now);
    }

    public void loadPokemon(JPanel panel)
    {
        //check the panel has data
        if (pokemonMap.containsKey(currentSelectedPanel))
        {
            //show the data
            PokemonIndividualData now = pokemonMap.get(currentSelectedPanel);
            nickNameField.setText(now.nickName);
            hpField.setText(Integer.toString(now.pokemonValueDate[0]));
            atkField.setText(Integer.toString(now.pokemonValueDate[1]));
            defField.setText(Integer.toString(now.pokemonValueDate[2]));
            spAtkField.setText(Integer.toString(now.pokemonValueDate[3]));
            spDefField.setText(Integer.toString(now.pokemonValueDate[4]));
            speedField.setText(Integer.toString(now.pokemonValueDate[5]));
        }
        else
        {
            nickNameField.setText("");
            hpField.setText("");
            atkField.setText("");
            defField.setText("");
            spAtkField.setText("");
            spDefField.setText("");
            speedField.setText("");
        }
    }
    public boolean checkValueRight()
    {
        //no input ->set to 0
        if(hpField.getText().equals(""))
            hpField.setText("0");
        if(atkField.getText().equals(""))
            atkField.setText("0");
        if(defField.getText().equals(""))
            defField.setText("0");
        if(spAtkField.getText().equals(""))
            spAtkField.setText("0");
        if(spDefField.getText().equals(""))
            spDefField.setText("0");
        if(speedField.getText().equals(""))
            speedField.setText("0");


        //check the value is correct
        boolean flag = true;
        int [] val = {
                Integer.valueOf(hpField.getText()),
                Integer.valueOf(atkField.getText()),
                Integer.valueOf(defField.getText()),
                Integer.valueOf(spAtkField.getText()),
                Integer.valueOf(spDefField.getText()),
                Integer.valueOf(speedField.getText()),
        };
        for(int c1=0;c1<6;c1++)
        {
            if(val[c1]<0||val[c1]>31)
            {
                flag = false;
            }
        }
        return flag;
    }
    void savedata(String path) throws IOException
    {
        //tranfer the map to array and store in the json
        FileWriter p = new FileWriter(path);
        JsonWriter writer = new JsonWriter(p);
        Gson gson = new Gson();
        ArrayList<PokemonIndividualData> arr =new ArrayList<>();
        for(Map.Entry<JPanel, PokemonIndividualData> entry : pokemonMap.entrySet())
        {
            arr.add(entry.getValue());
        /*gson.toJson(pokemonMap.values(), PokemonIndividualData.class, writer);
            JsonObject jsonObject = new JsonObject();
            jsonObject.getAsJsonObject(pokemonMap.toString());*/
        }
        gson.toJson(arr.toArray(),PokemonIndividualData[].class,writer);
        writer.close();
    }


    public static void main(String[] args) {
        JFrame frame = new JFrame("PokeGen");
        frame.setContentPane(new PokeGen().root);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

}