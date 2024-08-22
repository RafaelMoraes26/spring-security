package estudos.spring_secutiry_auth.config;

import java.io.IOException;
import java.util.Collections;

import org.springframework.web.filter.GenericFilterBean;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoggingFilter extends GenericFilterBean {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
        throws IOException, ServletException {

        // Converte o ServletRequest genérico em um HttpServletRequest específico, que fornece métodos mais específicos para lidar com requisições HTTP.
        HttpServletRequest req = (HttpServletRequest) request;

        // Tenta obter a sessão HTTP atual sem criar uma nova sessão se ela não existir.
        HttpSession session = req.getSession(false);

        if (session != null) {
            log.info("Session ID: " + session.getId());
            log.info("Session Attributes: " + Collections.list(session.getAttributeNames()));
        } else {
            log.info("No session found.");
        }

        // Passa a requisição e a resposta para o próximo filtro na cadeia de filtros.
        chain.doFilter(request, response);
    }
}

