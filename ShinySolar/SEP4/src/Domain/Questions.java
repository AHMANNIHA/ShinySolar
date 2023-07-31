package Domain;

import Conectivity.Database;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.TextInputDialog;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Questions {
    public Panel addPanel(ArrayList<String> manufacturers) {
        return updatePanel(column(), row(), manufacturers);
    }

    private int column() {
        int column = 0;
        TextInputDialog dialogArea = new TextInputDialog("");
        dialogArea.setTitle("Add column");
        dialogArea.setHeaderText("Column");
        Optional<String> resultArea = dialogArea.showAndWait();
        if (resultArea.isPresent()) {
            column = Integer.parseInt(resultArea.get());
        }
        return column;
    }

    private int row() {
        int row = 0;
        TextInputDialog dialogArea = new TextInputDialog("");
        dialogArea.setTitle("Add panel");
        dialogArea.setHeaderText("Row");
        Optional<String> resultArea = dialogArea.showAndWait();
        if (resultArea.isPresent()) {
            row = Integer.parseInt(resultArea.get());
        }
        return row;
    }

    public Panel updatePanel(int col, int row, ArrayList<String> manufacturers) {
        Database database = new Database();
        Panel panel = null;
                        try {
                            String panelType;
                            List<String> typeChoice = new ArrayList<>();
                            typeChoice.add("PV");
                            typeChoice.add("TH");
                            ChoiceDialog<String> dialogType = new ChoiceDialog<>("Select", typeChoice);
                            dialogType.setTitle("Add panel");
                            dialogType.setHeaderText("Type");
                            Optional<String> resultType = dialogType.showAndWait();
                            if (resultType.isPresent()) {
                                panelType = resultType.get();
                                try {
                                    String manufacturerName;
                                    ChoiceDialog<String> dialogName = new ChoiceDialog<>("Select", manufacturers);
                                    dialogName.setTitle("Add panel");
                                    dialogName.setHeaderText("Manufacturer");
                                    Optional<String> resultName = dialogName.showAndWait();
                                    if (resultName.isPresent()) {
                                        manufacturerName = resultName.get();
                                        try {
                                            int manufacturerId = database.manufacturerId(manufacturerName);
                                            double surfaceArea;
                                            TextInputDialog dialogArea = new TextInputDialog("");
                                            dialogArea.setTitle("Add panel");
                                            dialogArea.setHeaderText("Surface area");
                                            Optional<String> resultArea = dialogArea.showAndWait();
                                            if (resultArea.isPresent()) {
                                                surfaceArea = Double.parseDouble(resultArea.get());
                                                try {
                                                    String installationDate = "";
                                                    TextInputDialog dialogDate = new TextInputDialog("");
                                                    dialogDate.setTitle("Add panel");
                                                    dialogDate.setHeaderText("Installation date (format:YYYY-MM-DD)");
                                                    Optional<String> resultDate = dialogDate.showAndWait();
                                                    if (resultDate.isPresent()) {
                                                        installationDate = resultDate.get();
                                                    }
                                                    panel = new Panel(panelType, col, row, manufacturerId, surfaceArea, installationDate);
                                                } catch (Exception ignore) {
                                                }
                                            }
                                        } catch (Exception ignore) {
                                        }
                                    }
                                } catch (Exception ignore) {
                                }
                            }
                        } catch (Exception ignore) {}
        return panel;
    }
    public Manufacturer addManufacturer() {
        Manufacturer manufacturer = null;
        try {
            String name;
            TextInputDialog dialogName = new TextInputDialog("");
            dialogName.setTitle("Add manufacturer");
            dialogName.setHeaderText("Name e.g.:Solar A/S");
            Optional<String> resultName = dialogName.showAndWait();
            if (resultName.isPresent()) {
                name = resultName.get();
                try {
                    String address;
                    TextInputDialog dialogAddress = new TextInputDialog("");
                    dialogAddress.setTitle("Add manufacturer");
                    dialogAddress.setHeaderText("Address e.g.:Industrious Vest 43 6600 Venv");
                    Optional<String> resultAddress = dialogAddress.showAndWait();
                    if (resultAddress.isPresent()) {
                        address = resultAddress.get();
                        try {
                            String phone;
                            TextInputDialog dialogPhone = new TextInputDialog("");
                            dialogPhone.setTitle("Add manufacturer");
                            dialogPhone.setHeaderText("Phone e.g.: +76527000");
                            Optional<String> resultPhone = dialogPhone.showAndWait();
                            if (resultPhone.isPresent()) {
                                phone = resultPhone.get();
                                try {
                                    String email;
                                    TextInputDialog dialogEmail = new TextInputDialog("");
                                    dialogEmail.setTitle("Add manufacturer");
                                    dialogEmail.setHeaderText("Website e.g.:kundeservice@solar.dk");
                                    Optional<String> resultEmail = dialogEmail.showAndWait();
                                    if (resultEmail.isPresent()) {
                                        email = resultEmail.get();
                                        try {
                                            String website = "";
                                            TextInputDialog dialogWebsite = new TextInputDialog("");
                                            dialogWebsite.setTitle("Add manufacturer");
                                            dialogWebsite.setHeaderText("Website e.g.: www.solar.dk");
                                            Optional<String> resultWebsite = dialogWebsite.showAndWait();
                                            if (resultWebsite.isPresent()) {
                                                website = resultWebsite.get();
                                            }
                                            manufacturer = new Manufacturer(name, address, phone, email, website);
                                        }catch (Exception ignore){}
                                    }
                                }
                                catch (Exception ignore){}
                            }
                        } catch (Exception ignore){}
                    }
                } catch (Exception ignore){}
            }
        }
        catch (Exception ignore){}
        return manufacturer;
    }
}