package ru.stqa.addressbok.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoughtworks.xstream.XStream;
import ru.stqa.addressbok.model.PersonData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;


public class PersonDataGenerator {

    @Parameter(names = "-c", description = "Person count")
    public int count;

    @Parameter(names = "-f", description = "Target file")
    public String file;

    @Parameter(names = "-d", description = "Data format")
    public String format;

    public static void main(String[] args) throws IOException {
        PersonDataGenerator generator = new PersonDataGenerator();
        JCommander jCommander = new JCommander(generator);
        try{
            jCommander.parse(args);
        } catch (ParameterException ex){
            jCommander.usage();
            return;
        }
        generator.run();
    }

    private void run() throws IOException {
        List<PersonData> persons = generatePerson(count);
        if (format.equals("xml")){
            saveAsXml(persons, new File(file));
        }  else if (format.equals("json")){
            saveAsJson(persons, new File(file));
        } else{
            System.out.println("Unrecognized format " + format);
        }

    }

    private void saveAsJson(List<PersonData> persons, File file) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
        String json = gson.toJson(persons);
        Writer writer = new FileWriter(file);
        writer.write(json);
        writer.close();
    }

    private void saveAsXml(List<PersonData> persons, File file) throws IOException {
        XStream xstream = new XStream();
        xstream.processAnnotations(PersonData.class);
        String xml = xstream.toXML(persons);
        Writer writer = new FileWriter(file);
        writer.write(xml);
        writer.close();
    }

    private List<PersonData> generatePerson(int count) {
        List<PersonData> persons = new ArrayList<PersonData>();
        for (int i = 0; i < count; i++){
            persons.add(new PersonData()
                    .withFirstName(String.format("FirstName %s", i))
                    .withMiddleName(String.format("MiddleName %s", i))
                    .withLastName(String.format("LastName %s", i))
                    .withAddress(String.format("Address %s", i))
                    .withPhone(String.format("12345789%s", i))
                    .withEmail(String.format("test%s@test.test", i))
            );
        }
        return persons;
    }
}