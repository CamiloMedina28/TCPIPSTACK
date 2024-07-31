package cmedina.cirion.tcpipstack.GlobalVariables;

import cmedina.cirion.tcpipstack.TcpipstackApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class LoggerGen {

    Logger loggergenerator = LoggerFactory.getLogger(TcpipstackApplication.class);

    public Logger getLoggergenerator() {
        return loggergenerator;
    }


}

