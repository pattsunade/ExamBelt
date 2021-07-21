package com.patriciadelgado.consumidores.Services;

import java.util.List;
import java.util.Optional;

import com.patriciadelgado.consumidores.Models.Subs;
import com.patriciadelgado.consumidores.Repositories.SubsRepository;

import org.springframework.stereotype.Service;

@Service
public class SubsService {
    private final SubsRepository subsRepository;

    public SubsService(SubsRepository subsRepository) {
        this.subsRepository = subsRepository;
    }
    public List<Subs> getAll(){
		return (List<Subs>) subsRepository.findAll();
	}
	public Subs findSubscriptionById(Long id) {
		Optional<Subs> optionalSub = subsRepository.findById(id);
		if (optionalSub.isPresent()) {
            return optionalSub.get();
		} else {
			return null;
		}
	}
	public void createSub(Subs sub) {
		subsRepository.save(sub);
	}
	public void updateSub(Subs sub) {
		subsRepository.save(sub);
	}

	public void deleteSub(Subs subs) {
		subsRepository.delete(subs);
	}

	public List<Subs> orderByDesc() {
		return subsRepository.findAllByOrderByUsersDesc();
	}
	public Subs findSus(String name) {
        return subsRepository.findByNameContaining(name);
    }
}
