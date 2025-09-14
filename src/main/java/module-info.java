module com.antonymtranslation.antonymtranslation {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;

    opens com.antonymtranslation.antonymtranslation to javafx.fxml;
    exports com.antonymtranslation.antonymtranslation;
}