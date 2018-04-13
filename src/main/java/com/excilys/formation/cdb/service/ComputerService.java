package com.excilys.formation.cdb.service;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.excilys.formation.cdb.dao.ComputerDao;
import com.excilys.formation.cdb.exceptions.DaoException;
import com.excilys.formation.cdb.exceptions.ServiceException;
import com.excilys.formation.cdb.model.Computer;
import com.excilys.formation.cdb.validator.ComputerValidator;

@Service("computerServiceBean")
public class ComputerService {
    @Autowired
    private ComputerDao computerDao;

    static final Logger LOGGER = LoggerFactory.getLogger(ComputerService.class);

    public List<Computer> getList(int offset, int nbToPrint, String order, boolean desc) throws ServiceException {
        if (nbToPrint >= 1) {
            try {
                return computerDao.list(offset, nbToPrint, order, desc);
            } catch (DaoException e) {
                LOGGER.error("Error while listing computers from {} to {}", offset, offset + nbToPrint, e);
                throw(new ServiceException("Error while listing computers from " + offset + " to " + (offset + nbToPrint), e));
            }
        }

        return new ArrayList<>();
    }

    public List<Computer> getSearchList(int offset, int nbToPrint, String order, boolean desc, String search) throws ServiceException {
        if (nbToPrint >= 1) {
            try {
                return computerDao.listSearch(offset, nbToPrint, order, desc, search);
            } catch (DaoException e) {
                LOGGER.error("Error while listing search results from {} to {}", offset, offset + nbToPrint, e);
                throw(new ServiceException("Error while listing search results from " + offset + " to " + (offset + nbToPrint), e));
            }
        }

        return new ArrayList<>();
    }

    public Optional<Computer> getById(long id) throws ServiceException {
        if (id > 0) {
            try {
                return computerDao.read(id);
            } catch (DaoException e) {
                LOGGER.error("Error while reading details of computer n°{} ", id, e);
                throw(new ServiceException("Error while reading details of computer n°" + id, e));
            }
        }

        return Optional.empty();
    }

    public long getNbFound() throws ServiceException {
        try {
            return computerDao.count();
        } catch (DaoException e) {
            LOGGER.error("Error while counting computers in database", e);
            throw(new ServiceException("Error while counting computers in database", e));
        }
    }

    public long getNbSearch(String search) throws ServiceException {
        try {
            return computerDao.countSearch(search);
        } catch (DaoException e) {
            LOGGER.error("Error while counting search results in database", e);
            throw(new ServiceException("Error while counting search results in database", e));
        }
    }

    public boolean deleteById(long id) throws ServiceException {
        if (id > 0) {
            try {
                computerDao.delete(id);
            } catch (DaoException e) {
                LOGGER.error("Error while deleting company n°{}", id, e);
                throw(new ServiceException("Error while deleting company n°" + id, e));
            }
            return true;
        }

        return false;
    }

    public void setName(String name, Computer computer) {
        if (name.isEmpty()) {
            LOGGER.error("Name is mandatory, aborting creation");
        } else {
            computer.setName(name);
        }
    }

    public boolean setIntroDate(String intro, Computer computer) {
        if (!intro.isEmpty()) {
            try {
                computer.setIntroduced(LocalDate.parse(intro));
            } catch (DateTimeParseException e) {
                return false;
            }
        } else {
            computer.setIntroduced(null);
        }

        return true;
    }

    public boolean setDiscontDate(String discont, Computer computer) {
        if (!discont.isEmpty()) {
            try {
                computer.setDiscontinued(LocalDate.parse(discont));
            } catch (DateTimeParseException e) {
                return false;
            }
        } else {
            computer.setDiscontinued(null);
        }

        return true;
    }

    public Computer createComputer(Computer computer) throws ServiceException {
        if (!ComputerValidator.INSTANCE.validateComputer(computer)) {
            return null;
        }

        try {
            return computerDao.create(computer);
        } catch (DaoException e) {
            LOGGER.error("Error while creating a new computer", e);
            throw(new ServiceException("Error while creating a new computer", e));
        }
    }

    public Computer updateComputer(Computer computer) throws ServiceException {
        if (!ComputerValidator.INSTANCE.validateComputer(computer)) {
            return null;
        }

        try {
            return computerDao.update(computer);
        } catch (DaoException e) {
            LOGGER.error("Error while updating computer", e);
            throw(new ServiceException("Error while updating computer", e));
        }
    }

    public boolean deleteManyById(List<Long> ids) throws ServiceException {
        boolean elementsValid = true;
        for(Long id : ids) {
            elementsValid = elementsValid && (id > 0);
        }
        
        if (elementsValid) {
            try {
                computerDao.deleteMany(ids);
            } catch (DaoException e) {
                LOGGER.error("Error while deleting companies {}", ids, e);
                throw(new ServiceException("Error while deleting companies "+ ids, e));
            }
            return true;
        }

        return false;
    }
}
