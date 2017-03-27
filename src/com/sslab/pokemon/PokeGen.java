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
                int index = speciesComboBox.getSelectedIndex();
                if (!pokemonMap.containsKey(currentSelectedPanel))
                {
                    PokemonSpeciesData now = pokedex.getPokemonData(index);
                    nickNameField.setText(now.getSpeciesName());
                    hpField.setText(Integer.toString(0));
                    atkField.setText(Integer.toString(0));
                    defField.setText(Integer.toString(0));
                    spAtkField.setText(Integer.toString(0));
                    spDefField.setText(Integer.toString(0));
                    speedField.setText(Integer.toString(0));
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
                    PokemonIndividualData nowIndividualData= new PokemonIndividualData(nickNameField.getText(),index,val);
                    pokemonMap.put(currentSelectedPanel,nowIndividualData);


                }
                else
                {
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
                slot0.setBorder(BorderFactory.createBevelBorder(1));
                slot1.setBorder(BorderFactory.createBevelBorder(0));
                slot2.setBorder(BorderFactory.createBevelBorder(0));
                slot3.setBorder(BorderFactory.createBevelBorder(0));
                slot4.setBorder(BorderFactory.createBevelBorder(0));
                slot5.setBorder(BorderFactory.createBevelBorder(0));



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
                if(pokemonMap.containsKey(currentSelectedPanel))
                {
                    if (!checkValueRight())
                    {
                        JOptionPane.showMessageDialog(null, "Please check all your value is between 0 and31");
                        return;
                    }
                    else
                    {
                        setPokemon(currentSelectedPanel);
                    }
                }

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
                if(pokemonMap.containsKey(currentSelectedPanel))
                {
                    int n = JOptionPane.showConfirmDialog(
                            null,
                            "Do you sure you want to delete this pokemon?",
                            "Delete Warning",
                            JOptionPane.YES_NO_OPTION);
                    if(n == JOptionPane.YES_OPTION)
                    {
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
                    else
                        return;
                }
                else
                {
                    return;
                }
            }
        });

    }

    public void setPokemon(JPanel panel) {
        PokemonIndividualData now=pokemonMap.get(currentSelectedPanel);
        now.nickName = nickNameField.getText();
        now.pokemonValueDate[0]=(Integer.valueOf(hpField.getText()));
        now.pokemonValueDate[1]=(Integer.valueOf(atkField.getText()));
        now.pokemonValueDate[2]=(Integer.valueOf(defField.getText()));
        now.pokemonValueDate[3]=(Integer.valueOf(spAtkField.getText()));
        now.pokemonValueDate[4]=(Integer.valueOf(spDefField.getText()));
        now.pokemonValueDate[5]=(Integer.valueOf(speedField.getText()));
        pokemonMap.put(currentSelectedPanel,now);
    }

    public void loadPokemon(JPanel panel)
    {
        if (pokemonMap.containsKey(currentSelectedPanel))
        {
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