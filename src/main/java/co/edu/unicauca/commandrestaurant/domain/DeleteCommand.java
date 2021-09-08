package co.edu.unicauca.commandrestaurant.domain;

import co.edu.unicauca.commandrestaurant.access.IFoodRepository;
import co.edu.unicauca.commandrestaurant.access.RepositoryFactory;
import co.edu.unicauca.commandrestaurant.domain.service.FoodService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Beca98
 */
public class DeleteCommand extends Command {

    /**
     * Comida a Eliminar
     */
    private Food food;
    /**
     * Instancia al receptor
     */
    private FoodService service;

    public DeleteCommand(Food food) {
        this.food = food;
        IFoodRepository repo = RepositoryFactory.getInstance().getRepository("default");
        service = new FoodService(repo);
        this.hasUndo = true;
    }

    @Override
    public void execute() {
        Logger logger = LoggerFactory.getLogger(DeleteCommand.class);
        logger.info("Comando de Eliminacion ejecutado. Se elimino la comida " + food.toString());
        service.delete(food.getId());
    }

    @Override
    public void undo() {
        Logger logger = LoggerFactory.getLogger(CreateCommand.class);
        logger.info("Comando de Eliminacion se ha deshecho. Se creo la comida " + food.toString());
        service.create(food);
    }

}
