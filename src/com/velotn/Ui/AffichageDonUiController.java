package com.velotn.Ui;

import com.jfoenix.controls.JFXTextField;
import com.velotn.Entite.Don;
import com.velotn.Service.ServiceDon;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;
import javafx.util.converter.LocalDateStringConverter;
import org.supercsv.cellprocessor.FmtDate;
import org.supercsv.cellprocessor.ParseDate;
import org.supercsv.cellprocessor.ParseDouble;
import org.supercsv.cellprocessor.constraint.NotNull;
import org.supercsv.cellprocessor.constraint.Strlen;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AffichageDonUiController implements Initializable {


    @FXML
    private JFXTextField search_sum_field;

    @FXML
    private TableView<Don> tableDons;

    @FXML
    private TableColumn<Don, Double> sommeDon;

    @FXML
    private TableColumn<Don, LocalDate> dateDon;

    @FXML
    private TableColumn<Don, Integer> userDon;

    ServiceDon serviceDon = new ServiceDon();

    List<Don> dons = new ArrayList<>();
    private final ObservableList<Don> data = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            afficher();
            rechercher();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void afficher() throws SQLException {
        data.clear();
        dons =serviceDon.readAll() ;
        data.addAll(dons);

        sommeDon.setCellValueFactory(new PropertyValueFactory<>("somme"));
        dateDon.setCellValueFactory(new PropertyValueFactory<>("date"));
        userDon.setCellValueFactory(new PropertyValueFactory<>("userId"));

        tableDons.setItems(data);
        tableDons.setEditable(true);

        sommeDon.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        dateDon.setCellFactory(TextFieldTableCell.forTableColumn(new LocalDateStringConverter()));
        userDon.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter())) ;
    }

    @FXML
    void change_sum(TableColumn.CellEditEvent bb) throws SQLException {
        Don tab_donSelected =tableDons.getSelectionModel().getSelectedItem();
        tab_donSelected.setSomme(Double.parseDouble(bb.getNewValue().toString()));
        serviceDon.update(tab_donSelected);
    }

    @FXML
    void delete_don(ActionEvent event) throws SQLException {
        tableDons.setItems(data);
        ObservableList<Don> allDemandes,SingleDemandes ;
        allDemandes=tableDons.getItems();
        SingleDemandes=tableDons.getSelectionModel().getSelectedItems();
        Don A = SingleDemandes.get(0);
        ServiceDon STP = new ServiceDon();
        STP.delete(A);
        SingleDemandes.forEach(allDemandes::remove);

    }

    @FXML
    void rechercher() {
        FilteredList<Don> filteredData = new FilteredList<>(data, b -> true);

        search_sum_field.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(tab_promotion -> {


                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }


                String lowerCaseFilter = newValue.toLowerCase();

                if (String.valueOf(tab_promotion.getSomme()).indexOf(lowerCaseFilter)!= -1 ) {
                    return true;
                }
                else if (String.valueOf(tab_promotion.getId()).indexOf(lowerCaseFilter)!=-1)
                    return true;
                else
                    return false;
            });
        });

        SortedList<Don> sortedData = new SortedList<>(filteredData);

        sortedData.comparatorProperty().bind(tableDons.comparatorProperty());


        tableDons.setItems(sortedData);

    }

    static void writeCSVFile(String csvFileName, List<Don> listDons) {
        ICsvBeanWriter beanWriter = null;
        CellProcessor[] processors = new CellProcessor[] {
                 // price,
                new NotNull(), // published date
                new ParseDouble(),
        };

        try {
            beanWriter = new CsvBeanWriter(new FileWriter(csvFileName),
                    CsvPreference.STANDARD_PREFERENCE);
            String[] header = {"date", "somme"};
            beanWriter.writeHeader(header);

            for (Don aBook : listDons) {
                beanWriter.write(aBook, header, processors);
            }

        } catch (IOException ex) {
            System.err.println("Error writing the CSV file: " + ex);
        } finally {
            if (beanWriter != null) {
                try {
                    beanWriter.close();
                } catch (IOException ex) {
                    System.err.println("Error closing the writer: " + ex);
                }
            }
        }
    }

    @FXML
    void telecharger_csv(ActionEvent event) {
        writeCSVFile("hello.csv", data);
    }
}



