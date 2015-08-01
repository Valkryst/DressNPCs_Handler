package services;

import files.FileInput;
import misc.Logger;
import objects.Model;
import org.apache.commons.lang3.exception.ExceptionUtils;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public class LoadManager {
    /**
     * Attempts to load a model from the specified file path.
     * @param path The path to the model to load.
     * @return The loaded model.
     */
    public static Model loadModelFromFile(final Path path) {
        final Model model = new Model();
        String[] data = new String[0];

        try {
            final List<String> settingsFile = FileInput.readEntireFile(path, true);
            data = settingsFile.toArray(new String[settingsFile.size()]);
        } catch(final IOException e) {
            Logger.writeLog(e.getMessage() + "\n\n" + ExceptionUtils.getStackTrace(e), Logger.LOG_TYPE_ERROR);
            System.exit(1);
        }

        String[] temp;

        for(final String s : data) {
            temp = s.split("=");

            switch(temp[0]) {
                case "modelEntryID": {
                    if(temp.length == 2) {
                        model.setEntry(Integer.valueOf(temp[1]));
                    }

                    break;
                }
                case "race": {
                    if(temp.length == 2) {
                        model.setRace(Byte.valueOf(temp[1]));
                    }

                    break;
                }
                case "gender": {
                    if(temp.length == 2) {
                        model.setGender(Byte.valueOf(temp[1]));
                    }

                    break;
                }
                case "skin": {
                    if(temp.length == 2) {
                        model.setSkin(Byte.valueOf(temp[1]));
                    }

                    break;
                }
                case "face": {
                    if(temp.length == 2) {
                        model.setFace(Byte.valueOf(temp[1]));
                    }

                    break;
                }
                case "hair": {
                    if(temp.length == 2) {
                        model.setHair(Byte.valueOf(temp[1]));
                    }

                    break;
                }
                case "hairColor": {
                    if(temp.length == 2) {
                        model.setHairColor(Byte.valueOf(temp[1]));
                    }

                    break;
                }
                case "facialHair": {
                    if(temp.length == 2) {
                        model.setFacialHair(Byte.valueOf(temp[1]));
                    }

                    break;
                }
                case "head": {
                    if(temp.length == 2) {
                        model.setHead(Integer.valueOf(temp[1]));
                    }

                    break;
                }
                case "shoulders": {
                    if(temp.length == 2) {
                        model.setShoulders(Integer.valueOf(temp[1]));
                    }

                    break;
                }
                case "body": {
                    if(temp.length == 2) {
                        model.setBody(Integer.valueOf(temp[1]));
                    }

                    break;
                }
                case "chest": {
                    if(temp.length == 2) {
                        model.setChest(Integer.valueOf(temp[1]));
                    }

                    break;
                }
                case "waist": {
                    if(temp.length == 2) {
                        model.setWaist(Integer.valueOf(temp[1]));
                    }

                    break;
                }
                case "legs": {
                    if(temp.length == 2) {
                        model.setLegs(Integer.valueOf(temp[1]));
                    }

                    break;
                }
                case "feet": {
                    if(temp.length == 2) {
                        model.setFeet(Integer.valueOf(temp[1]));
                    }

                    break;
                }
                case "wrists": {
                    if(temp.length == 2) {
                        model.setWrists(Integer.valueOf(temp[1]));
                    }

                    break;
                }
                case "hands": {
                    if(temp.length == 2) {
                        model.setHands(Integer.valueOf(temp[1]));
                    }

                    break;
                }
                case "back": {
                    if(temp.length == 2) {
                        model.setBack(Integer.valueOf(temp[1]));
                    }

                    break;
                }
                case "tabard": {
                    if(temp.length == 2) {
                        model.setTabard(Integer.valueOf(temp[1]));
                    }

                    break;
                }
            }
        }

        return model;
    }
}
