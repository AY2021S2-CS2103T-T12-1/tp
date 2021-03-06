package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.Wardrobe;
import seedu.address.testutil.TypicalPersons;

public class JsonSerializableWardrobeTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableWardrobeTest");
    private static final Path TYPICAL_PERSONS_FILE = TEST_DATA_FOLDER.resolve("typicalPersonsWardrobe.json");
    private static final Path INVALID_PERSON_FILE = TEST_DATA_FOLDER.resolve("invalidPersonWardrobe.json");
    private static final Path DUPLICATE_PERSON_FILE = TEST_DATA_FOLDER.resolve("duplicatePersonWardrobe.json");

    @Test
    public void toModelType_typicalPersonsFile_success() throws Exception {
        JsonSerializableWardrobe dataFromFile = JsonUtil.readJsonFile(TYPICAL_PERSONS_FILE,
                JsonSerializableWardrobe.class).get();
        Wardrobe wardrobeFromFile = dataFromFile.toModelType();
        Wardrobe typicalPersonsWardrobe = TypicalPersons.getTypicalWardrobe();
        assertEquals(wardrobeFromFile, typicalPersonsWardrobe);
    }

    @Test
    public void toModelType_invalidPersonFile_throwsIllegalValueException() throws Exception {
        JsonSerializableWardrobe dataFromFile = JsonUtil.readJsonFile(INVALID_PERSON_FILE,
                JsonSerializableWardrobe.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicatePersons_throwsIllegalValueException() throws Exception {
        JsonSerializableWardrobe dataFromFile = JsonUtil.readJsonFile(DUPLICATE_PERSON_FILE,
                JsonSerializableWardrobe.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableWardrobe.MESSAGE_DUPLICATE_PERSON,
                dataFromFile::toModelType);
    }

}
