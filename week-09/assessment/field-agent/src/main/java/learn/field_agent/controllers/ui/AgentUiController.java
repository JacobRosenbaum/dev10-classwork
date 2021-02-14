package learn.field_agent.controllers.ui;

import learn.field_agent.domain.AgentService;
import learn.field_agent.domain.Result;
import learn.field_agent.models.Agent;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/agents")
public class AgentUiController {
    private final AgentService agentService;

    public AgentUiController(AgentService agentService) {
        this.agentService = agentService;
    }

    @GetMapping
    public String index(Model model) {
        List<Agent> agents = agentService.findAll();
        model.addAttribute("agents", agents);
        return "agents/index";
    }

    @GetMapping("/create")
    public String create(@ModelAttribute("agent") Agent agent) {
        agent.setHeightInInches(36);
        return "agents/form";
    }

    @PostMapping("/create")
    public String create(
            @ModelAttribute("agent") @Valid Agent agent,
            BindingResult result, Model model) {

        if (result.hasErrors()) {
            System.out.println(result.getAllErrors());
            return "agents/form";
        }

        Result<Agent> serviceResult = agentService.add(agent);

        if (!serviceResult.isSuccess()) {
            for (String message : serviceResult.getMessages()) {
                result.addError(new ObjectError("agent", message));
            }

            return "agents/form";
        }

        return "redirect:/agents";
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable int id, Model model) {
        Agent agent = agentService.findById(id);

        if (agent == null) {
            return "not-found";
        }

        model.addAttribute("agent", agent);

        return "agents/form";
    }

    @PostMapping("/update/*")
    public String update(
            @ModelAttribute("agent") @Valid Agent agent,
            BindingResult result, Model model) {

        if (result.hasErrors()) {
            return "agents/form";
        }

        Result<Agent> serviceResult = agentService.update(agent);

        if (!serviceResult.isSuccess()) {
            for (String message : serviceResult.getMessages()) {
                result.addError(new ObjectError("agent", message));
            }
            return "agents/form";
        }

        return "redirect:/agents";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id, Model model) {
        Agent agent = agentService.findById(id);

        if (agent == null) {
            return "not-found";
        }

        model.addAttribute("agent", agent);

        return "agents/delete";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable int id) {
        Agent agent = agentService.findById(id);

        if (agent == null) {
            return "not-found";
        }

        agentService.deleteById(id);

        return "redirect:/agents";
    }

    @GetMapping("/missions")
    public String getMission() {
        return "/missions";
    }

    @GetMapping("/agencies")
    public String getAgency() {
        return "/agencies";
    }
}
