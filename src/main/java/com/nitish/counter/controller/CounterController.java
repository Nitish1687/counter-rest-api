package com.nitish.counter.controller;

import com.csvreader.CsvWriter;
import com.nitish.counter.dto.CountDto;
import com.nitish.counter.service.CountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
public class CounterController {

    @Autowired
    private CountService countService;

    @RequestMapping(value = "/counter-api/search", method = POST, consumes = "application/json")
    public ResponseEntity<Collection<CountDto>> search(@RequestBody Map<String, List<String>> searchTexts) {
        return new ResponseEntity<>(countService.countOccuranceFor(searchTexts), OK);
    }

    @RequestMapping(value = "/counter-api/top/{count}", method = GET, consumes = "application/json", produces = "text/csv")
    public void topText(@PathVariable int count, HttpServletResponse response) throws IOException {
        Collection<CountDto> higherOccuranceText = countService.getHigherCountTextUpTo(count);
        response.setHeader("Content-Disposition", "attachment; filename=yourData.csv");
        csvWrite(higherOccuranceText, response.getOutputStream());

    }

    private void csvWrite(Collection<CountDto> higherOccuranceText, ServletOutputStream outputStream) throws IOException {
        OutputStream buffOs = new BufferedOutputStream(outputStream);
        OutputStreamWriter outputwriter = new OutputStreamWriter(buffOs);

        CsvWriter writer = new CsvWriter(outputwriter, '\u0009');
        for (CountDto countDto : higherOccuranceText) {
            writer.write(countDto.getWord() + "|" + countDto.getCount()+ System.getProperty("line.separator"));
        }
        outputwriter.flush();
        outputwriter.close();
    }

}
