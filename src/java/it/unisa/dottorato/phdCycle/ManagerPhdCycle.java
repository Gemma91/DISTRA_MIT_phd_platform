package it.unisa.dottorato.phdCycle;

import it.unisa.dottorato.exception.ConnectionException;
import it.unisa.dottorato.exception.EntityNotFoundException;
import it.unisa.dottorato.utility.Utility;
import it.unisa.integrazione.database.DBConnection;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ManagerPhdCycle {

    /**
     * Il nome della tabella
     */
    private static final String TABLE_PHDCYCLE = "phdcycle";

    //	 istanza della classe
    private static ManagerPhdCycle instance;

    /**
     * Il costruttore della classe e' dichiarato privato, per evitare
     * l'istanziazione di oggetti della classe .
     */
    private ManagerPhdCycle() {
        super();
    }

    /**
     * Metodo della classe incaricato della produzione degli oggetti, tale
     * metodo deve essere chiamato per restituire l'istanza del Singleton.
     * L'oggetto Singleton sara' istanziato solo alla prima invocazione del
     * metodo. Nelle successive invocazioni, invece, sara' restituito un
     * riferimento allo stesso oggetto.
     *
     * @return L'istanza della classe
     */
    public static synchronized ManagerPhdCycle getInstance() {
        if (instance == null) {
            instance = new ManagerPhdCycle();
        }
        return instance;
    }

    /**
     * Metodo della classe incaricato dell'inserimento di una nuova entita'
     * nella tabella phdCycle del database.
     *
     * @param pCycle
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     * @throws it.unisa.dottorato.exception.EntityNotFoundException
     * @throws java.io.IOException
     * @throws it.unisa.dottorato.exception.ConnectionException
     */
    public synchronized void insert(PhdCycle pCycle) throws ClassNotFoundException, SQLException, IOException, EntityNotFoundException, ConnectionException {
        try (Connection connect = DBConnection.getConnection()) {

            /*
             * Prepariamo la stringa SQL per inserire un nuovo record 
             * nella tabella phdCycle
             */
            String tSql = "INSERT INTO "
                    + ManagerPhdCycle.TABLE_PHDCYCLE
                    + " (idPhdCycle, description, year, FK_professor)"
                    + " VALUES ('"
                    + pCycle.getIdPhdCycle()
                    + "','"
                    + pCycle.getDescription()
                    + "','"
                    + pCycle.getYear()
                    + "','"
                    + pCycle.getFK_Professor()
                    + "')";

            //Inviamo la Query al DataBase
            Utility.executeOperation(connect, tSql);

            connect.commit();
        }
    }
    
    /**
     * Metodo della classe incaricato della modifica di una nuova entita'
     * nella tabella phdCycle del database.
     *
     * @param pCycle
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     * @throws it.unisa.dottorato.exception.EntityNotFoundException
     * @throws java.io.IOException
     * @throws it.unisa.dottorato.exception.ConnectionException
     */
    public synchronized void update(PhdCycle pCycle) throws ClassNotFoundException, SQLException, IOException, EntityNotFoundException, ConnectionException {
        try (Connection connect = DBConnection.getConnection()) {

            /*
             * Prepariamo la stringa SQL per modificare un record 
             * nella tabella phdCycle
             */
            String tSql = "UPDATE "
                    + ManagerPhdCycle.TABLE_PHDCYCLE
                    + " set description = '"
                    + pCycle.getDescription()
                    + "', year = '"
                    + pCycle.getYear()
                    + "', FK_Professor = '"
                    + pCycle.getFK_Professor()
                    + "' WHERE idPhdCycle = '"
                    + pCycle.getIdPhdCycle()+"'";

            //Inviamo la Query al DataBase
            Utility.executeOperation(connect, tSql);

            connect.commit();
        }
    }
    
    /**
     * Metodo della classe incaricato della cancellazopme di una nuova entita'
     * nella tabella phdCycle del database.
     *
     * @param pCycle
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     * @throws it.unisa.dottorato.exception.EntityNotFoundException
     * @throws java.io.IOException
     * @throws it.unisa.dottorato.exception.ConnectionException
     */
    public synchronized void delete(PhdCycle pCycle) throws ClassNotFoundException, SQLException, IOException, EntityNotFoundException, ConnectionException {
        Connection connect = null;
        try {
            // Otteniamo una Connessione al DataBase
            connect = DBConnection.getConnection();

            /*
             * Prepariamo la stringa SQL per modificare un record 
             * nella tabella phdCycle
             */
            String tSql = "DELETE FROM "
                    + ManagerPhdCycle.TABLE_PHDCYCLE
                    + " WHERE idPhdCycle = '"
                    + pCycle.getIdPhdCycle()+"'";

            //Inviamo la Query al DataBase
            Utility.executeOperation(connect, tSql);

            connect.commit();
        } finally {
            DBConnection.releaseConnection(connect);
        }
    }
    
    /**
     * Metodo della classe incaricato della ricerca
     * di un ciclo contenuto nella tabella.
     *
     * @param idPhdCycle
     * @return 
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     * @throws it.unisa.dottorato.exception.EntityNotFoundException
     * @throws java.io.IOException
     * @throws it.unisa.dottorato.exception.ConnectionException
     */
    public synchronized PhdCycle getPhdCycleById(int idPhdCycle) throws ClassNotFoundException, SQLException, IOException, EntityNotFoundException, ConnectionException {
        Connection connect = null;
        try {
           PhdCycle cycle = new PhdCycle();
            // Otteniamo una Connessione al DataBase
            connect = DBConnection.getConnection();

            /*
             * Prepariamo la stringa SQL per modificare un record 
             * nella tabella phdCycle
             */
            String tSql = "SELECT * FROM "
                    + ManagerPhdCycle.TABLE_PHDCYCLE
                    + " WHERE idPhdCycle = '"
                    + idPhdCycle+"'";

            //Inviamo la Query al DataBase
            ResultSet result = Utility.queryOperation(connect, tSql);
            
            if(result.next())
            {
                cycle.setIdPhdCycle(result.getInt("idPhdCycle"));
                cycle.setDescription(result.getString("description"));
                cycle.setYear(result.getInt("year"));
            }
            
            return cycle;
            
        } finally {
            DBConnection.releaseConnection(connect);
        }
    }
    
    
        /**
     * Metodo della classe incaricato della ricerca
     * dell'ultimo ciclo contenuto nella tabella.
     *
     * @return 
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     * @throws it.unisa.dottorato.exception.EntityNotFoundException
     * @throws java.io.IOException
     * @throws it.unisa.dottorato.exception.ConnectionException
     */
    public synchronized int getLastCycle() throws ClassNotFoundException, SQLException, IOException, EntityNotFoundException, ConnectionException {
        Connection connect = null;
        try {
           int idCycle = 0;
            // Otteniamo una Connessione al DataBase
            connect = DBConnection.getConnection();

            /*
             * Prepariamo la stringa SQL per modificare un record 
             * nella tabella phdCycle
             */
                    
            String tSql = "SELECT min(idPhdCycle) as Minimo FROM "
                    + ManagerPhdCycle.TABLE_PHDCYCLE
                    + "'";

            //Inviamo la Query al DataBase
            ResultSet result = Utility.queryOperation(connect, tSql);
            
            if(result.next())
            {
                idCycle = result.getInt("Minimo");
            }
            
            return idCycle;
            
        } finally {
            DBConnection.releaseConnection(connect);
        }
    }
    
}